port module Main exposing (..)

import Browser
import Css exposing (alignItems, backgroundColor, borderRadius, borderRadius4, boxShadow5, center, color, column, cursor, displayFlex, flexDirection, fontFamily, fontSize, fontWeight, hex, hidden, hover, letterSpacing, maxWidth, overflow, padding4, pointer, property, px, rgba, sansSerif)
import Html.Styled.Events exposing (onClick)
import Html.Styled exposing (Html, article, button, div, em, li, main_, span, text, toUnstyled, ul)
import Html.Styled.Attributes exposing (css)
import Http exposing (Error(..), emptyBody, stringBody)
import Json.Decode as D exposing (Decoder, int, string)
import Json.Encode as E

main = Browser.element
           { init = init
           , update = update
           , subscriptions = subscriptions
           , view = view >> toUnstyled
           }

-- PORTS
port sendMessage : String -> Cmd msg
port messageReceiver : (E.Value -> msg) -> Sub msg
port clipboardCopy : String -> Cmd msg

-- MODEL
type alias Model =
    { status: Status
      , comments: (List CommentToValidate)
      , log: (List String)
    }

type Status = Connected | Disconnected

type alias LiveEvent =
    { eventType: String
    , comment: CommentToValidate
    }
type alias CommentToValidate =
    { id: String
    , commentId: String
    , user: User
    , item: Int
    , timeStamp: String
    , fullComment: String
    , action: String
    }

type alias User =
    {name: String
    ,id: String}

liveEventsDecoder: Decoder (List LiveEvent)
liveEventsDecoder = D.list (liveEventDecoder)

liveEventDecoder: Decoder LiveEvent
liveEventDecoder = D.map2 LiveEvent
            (D.field "eventType" string)
            (D.field "comment" commentDecoder)

commentDecoder: Decoder CommentToValidate
commentDecoder =
        D.map7 CommentToValidate
            (D.field "id" string)
            (D.field "commentId" string)
            (D.field "user" userDecoder)
            (D.field "item" int)
            (D.field "timestamp" string)
            (D.field "fullComment" string)
            (D.field "action" string)

userDecoder: Decoder User
userDecoder =
    D.map2 User
        (D.field "name" string)
        (D.field "id" string)

-- UPDATE

type Msg = Switch
            | NewCommentsRaw (E.Value) -- Coming from WebSocket
            | AuthorToClipboard (String)
            | CommentValidated (CommentToValidate)
            | LiveStarted (Result Http.Error String)
            | LiveStopped (Result Http.Error String)
            | LiveStatus (Result Http.Error String)

apiUrl = "http://localhost:8080/"

checkLive: () -> (Cmd Msg)
checkLive () =
    Http.get {
        url = apiUrl ++ "/live"
        ,expect = Http.expectString LiveStatus
    }

toggleLive: Status -> (Cmd Msg)
toggleLive status =
    if status /= Connected then
        Http.request
            { url = apiUrl ++ "/live"
              , method = "POST"
              , headers = []
              , body = stringBody "application/x-www-form-urlencoded" "video=fb-123&liveId=Live-2022-07-17&items=100"
              , expect = Http.expectString LiveStarted
              , timeout = Nothing
              , tracker = Nothing
            }
     else
        Http.request
            { url = apiUrl ++ "/live"
              , method = "DELETE"
              , headers = []
              , body = emptyBody
              , expect = Http.expectString LiveStopped
              , timeout = Nothing
              , tracker = Nothing
            }

errorToString : Http.Error -> String
errorToString error =
    case error of
        BadUrl url ->
            "The URL " ++ url ++ " was invalid"
        Timeout ->
            "Unable to reach the server, try again"
        NetworkError ->
            "Unable to reach the server, check your network connection"
        BadStatus 500 ->
            "The server had a problem, try again later"
        BadStatus 400 ->
            "Verify your information and try again"
        BadStatus _ ->
            "Unknown error"
        BadBody errorMessage ->
            errorMessage

init : () -> (Model,Cmd Msg)
init _ =
    ({status = Disconnected, comments = [] , log=[] }, checkLive ())


handleHttpError: Model -> Http.Error -> (Model, Cmd Msg)
handleHttpError model http =
    ( {model | log = (model.log ++ [errorToString http])}, Cmd.none)

update: Msg -> Model -> (Model, Cmd Msg)
update msg model =
    case msg of
        NewCommentsRaw rawComments ->
            case (D.decodeValue liveEventsDecoder rawComments) of
                Ok liveEvents -> ({model | comments= model.comments ++ (liveEvents |> List.map(\ev -> ev.comment)) },Cmd.none)
                Err _ -> ({model | log = model.log ++ ["Could not decode"]},Cmd.none)
        AuthorToClipboard name ->
            (model, clipboardCopy name)
        CommentValidated comment ->
            ({model |   comments = (model.comments |> List.filter (\c -> (comment.id /= c.id))) },
            sendMessage (comment.id) )
        Switch -> (model, toggleLive model.status)
        LiveStatus result ->
            case result of
                Ok response -> ({model | log = model.log ++[response], status = Connected },Cmd.none)
                Err http ->
                    case http of
                     BadStatus 404 -> ({model | status = Disconnected },Cmd.none)
                     _ -> handleHttpError model http
        LiveStarted result ->
            case result of
                Ok response -> ({model | status = Connected , log = model.log ++ [response]},Cmd.none)
                Err http -> handleHttpError model http
        LiveStopped result ->
            case result of
                Ok response -> ({model | status = Disconnected, log= model.log ++ [response]}, Cmd.none)
                Err http -> handleHttpError model http


-- SUBSCRIPTIONS


-- Subscribe to the `messageReceiver` port to hear about messages coming in
-- from JS. Check out the index.html file to see how this is hooked up to a
-- WebSocket.
--
subscriptions : Model -> Sub Msg
subscriptions _ =
  messageReceiver NewCommentsRaw

-- VIEW

view : Model -> Html Msg
view model =
    div [css [fontFamily sansSerif]]
    [ button [onClick Switch] [text (if model.status == Connected then "Stopper Live" else "Démarrer Live")]
    , div [] [
            model.log
                |> List.map(\l -> li [][text l])
                |> ul[]
        ]
    , displayListOfComments model
    ]


displayListOfComments : Model -> Html Msg
displayListOfComments model =
    model.comments
            |> List.map(\c -> articleFromComment c)
            |> main_ [
                css[
                        backgroundColor(hex "fff")
                        ,borderRadius4 (px 0) (px 0)  (px 12)  (px 12)
                        ,padding4 (px 30) (px 15) (px 20) (px 45)
                        ,displayFlex
                        ,flexDirection column
                        ,maxWidth(px 1024)
                        ,property "row-gap" "8px"
                    ]
            ]

articleFromComment : CommentToValidate -> Html Msg
articleFromComment comment =
    article  [
            css [
                        displayFlex
                        ,alignItems center
                        ,property "gap" "12px"
                        ,padding4 (px 10) (px 30) (px 10) (px 10)
                        ,overflow hidden
                        ,borderRadius (px 10)
                        ,boxShadow5 (px 0) (px 5) (px 7) (px -1) (rgba 51  51 51 0.23)
                        ,cursor pointer
                        ,backgroundColor (hex "fff")
                        ,hover[
                            boxShadow5 (px 0) (px 9) (px 47) (px 11) (rgba 51 51 51 0.18)
                            --,transform (scale 1.05)
                        ]
                    ]
                ] [
                    span [ onClick (AuthorToClipboard comment.user.name)
                        ,css [ color (hex "#979cb0")
                        ,fontWeight (Css.int 600)
                        ,fontSize (px 20)
                        ,letterSpacing (px 0.64)
                        --,width (px 200)
                        ,hover[
                            backgroundColor (hex "#e6e5d9")
                        ]
                       ]
                    ][text comment.user.name]
                    , span [onClick (CommentValidated comment)
                            ,css[]]
                            [text comment.action]
                    , span [] [text (String.fromInt comment.item)]
                    , span[] [em[] [text (comment.fullComment)]]
                   ]

