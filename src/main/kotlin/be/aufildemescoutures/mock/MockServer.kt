package be.aufildemescoutures.mock

import io.quarkus.arc.profile.UnlessBuildProfile
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.jboss.logging.Logger
import java.time.Duration
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import kotlin.random.Random

/**
 * I want to use the same mock server for dev and test mode to only have place where the fake comments are generated
 * Since it is not possible to use QuarkusTestResourceLifecycleManager resources in dev:mode (see https://github.com/quarkusio/quarkus/issues/20037)
 * Then I had to go with a custom made mock server
 */
@ApplicationScoped
@UnlessBuildProfile("prod")
@Path("mock")
class MockServer {
    private var LOG = Logger.getLogger(javaClass)
    private val names =
        arrayOf("Jeff Bezos", "Bill Gates", "Joe Biden", "Donald Trump", "Elon Musk", "Ada Lovelace", "Pikachu")

    @Inject
    lateinit var mockConfiguration: MockConfiguration

    private fun replacePlaceHolders(msg: String): String {
        val randomUser = Random.nextInt(0, names.size - 1)
        val randomId = Random.nextLong().toString()
        return msg.replace("%IDUSER", randomUser.toString())
            .replace("%NAME", names[randomUser])
            .replace("%ID", randomId)
    }

    private fun delaySendingMessage(msg: String): Uni<Any>? {
        val randomDelay = Duration.ofMillis(
            mockConfiguration.minMessageDelay
                    + Random.nextLong(mockConfiguration.maxMessageDelay - mockConfiguration.minMessageDelay)
        )
        return Uni.createFrom().nullItem<Any>().onItem().delayIt().by(randomDelay);
    }

    @Path("{video}/live_comments")
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun getComments(
        @PathParam("video") video: String,
        @QueryParam("access_token") accessToken: String?,
        @QueryParam("fields") fields: String?,
        @QueryParam("comment_rate") commentRate: String?
    ): Multi<String> {
        LOG.debug("New call to get comments")
        val split = comment_stream.split("\n")
        val maxSize:Int = mockConfiguration.totalNumber?:split.size
        return Multi.createFrom().iterable(split.subList(0,maxSize))
            .map(this::replacePlaceHolders)
            .onItem().call(this::delaySendingMessage)
            .map {
                LOG.debug("sending $it")
                it
            }
    }

    companion object {
        val comment_stream = """
    {"from":{"name":"%NAME","id":"%IDUSER"},"created_time":"2021-12-19T05:38:29+0000","message":"je prends le 5","id":"%ID"}
    {"from":{"name":"%NAME","id":"%IDUSER"},"created_time":"2021-12-19T05:38:40+0000","message":"revoir le 4, 2, 39 et le 11","id":"%ID"}
    {"from":{"name":"%NAME","id":"%IDUSER"},"created_time":"2021-12-19T05:43:40+0000","message":"Je voudrais revoir le 9 et le 1","id":"%ID"}
    {"from":{"name":"%NAME","id":"%IDUSER"},"created_time":"2021-12-19T05:44:40+0000","message":"Fake news, sleepy Joe !","id":"%ID"}
    {"created_time":"2021-12-19T05:44:40+0000","message":"Tr\u00e8s beau celui la aussi \ud83e\udd70","id":"%ID"}
    {"created_time":"2021-12-19T05:44:40+0000","message":"Je prend le n 10 svp","id":"%ID"}
    {"from":{"name":"%NAME","id":"%IDUSER"},"created_time":"2022-02-20T15:43:40+0000","message":"J'ach??te le 14 et le 22","id":"%ID"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:01:44+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:01:46+0000", "message":"Bonsoir :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:02:02+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:02:10+0000", "message":"Re ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:02:11+0000", "message":"Hello"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:02:13+0000", "message":"Bonsoir!"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:02:49+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:03:30+0000", "message":"????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:03:41+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:03:48+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:03:58+0000", "message":"Cc"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:04:02+0000", "message":"Bonsoir :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:04:13+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:04:37+0000", "message":"Bsr"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:04:55+0000", "message":"Bonsoiiiiiiir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:05:29+0000", "message":"Bonsoir ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:05:42+0000", "message":"Bonsoir ?? tout le monde"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:06:24+0000", "message":"Pr??te????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:06:31+0000", "message":"Oui coucou"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:07:02+0000", "message":"Cc bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:07:39+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:08:16+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:09:18+0000", "message":"Bonsoir ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:10:16+0000", "message":"Bonsoir. Zut zappe le d??but"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:10:59+0000", "message":"OK merciiiii"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:11:11+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:13:50+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:14:17+0000", "message":"Hello!!!! Ai rat?? le d??but???? zut alors????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:14:21+0000", "message":"Bonsoir ??????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:14:33+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:15:00+0000", "message":"Ah oui j ai particip?? aussi :-)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:15:15+0000", "message":"Oui ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:15:25+0000", "message":"Bonsoir !"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:15:38+0000", "message":"Bigre...je suis rep??r??e...."}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:16:36+0000", "message":"Bien le bonsoir ?? maman"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:16:54+0000", "message":"Bonsoir, pourriez vous rappeler les prix svp ? Je viens d'arriver ????????????????? merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:17:02+0000", "message":"Ah oui...bises ?? maman????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:17:10+0000", "message":"Bonsoir ?? vous aussi"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:17:36+0000", "message":"Merciiiii"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:17:38+0000", "message":"Sans rancune????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:18:36+0000", "message":"Bonsoir ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:19:05+0000", "message":"Bonsoir Val??rie"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:19:28+0000", "message":"Je prends le 12 ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:19:32+0000", "message":"Je prend"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:19:46+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:19:57+0000", "message":"Oui"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:20:10+0000", "message":"Oui le 20"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:20:13+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:21:32+0000", "message":"Possible de revoir 10, 11 et 17?  Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:23:47+0000", "message":"Cerisier ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:24:07+0000", "message":"Cerisier"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:24:44+0000", "message":"Cerisier ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:25:40+0000", "message":"Bonsoir ?? tous ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:26:36+0000", "message":"Cerisier aussi il me semble"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:27:01+0000", "message":"Peu importe il est beau ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:27:28+0000", "message":"Coucou ga??lle ????????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:28:19+0000", "message":"Hello!! J'arrive en route comment ??a marche?! Lol"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:28:49+0000", "message":"Cb le 30"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:28:53+0000", "message":"Je prend"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:28:59+0000", "message":"Ils sont moins cherrrrrrr haha, un signe pour mon anniv tu crois ??"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:29:02+0000", "message":"29"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:29:05+0000", "message":"Merciiii"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:29:09+0000", "message":"Il est ?? 42 euros :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:29:19+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:30:55+0000", "message":"Ouiiii canon celui-l??, moi aussi coup de c??ur"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:31:02+0000", "message":"Je l'ai avec du rose n??on celui l??, j'adore"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:31:34+0000", "message":"Idem Albert Einstein ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:31:50+0000", "message":"??????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:35:01+0000", "message":"Je peux mettre que je prends le 27 en attendant de le revoir et de voir le reste ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:35:32+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:36:06+0000", "message":"Bonsoir le 34 est a quel prix svp?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:36:27+0000", "message":"Il est ?? 42 euros :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:39:46+0000", "message":"Je peux mettre une option sur le 41, le temps de voir le reste ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:40:10+0000", "message":"Oui pas de soucis, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:40:15+0000", "message":"Il n y a qu un 41 ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:41:17+0000", "message":"Envoyez un message priv?? sur la page, No??mi pourra vous dire si elle peut le refaire ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:42:52+0000", "message":"Je veux bien revoir le 13"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:44:47+0000", "message":"C'est le t shirt kibrille qui fait ??a ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:44:49+0000", "message":"Je veux bien le 49! ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:45:08+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:45:12+0000", "message":"Non elle est belle, c'est tout ! ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:45:54+0000", "message":"C'est Alexandre qui a pris des pseudos ????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:46:34+0000", "message":"????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:46:48+0000", "message":"Avec une guerre de retard je veux bien le 5"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:47:14+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:48:09+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:48:29+0000", "message":"Bonsoir tout le monde ???? !"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:49:56+0000", "message":"Bonsoir! Vous ??tes toujours de bonne humeur c'est top!!"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:49:59+0000", "message":"Pour moi, le 55 !"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:50:16+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:50:50+0000", "message":"Bonsoir , je prends le 54 si toujours dispo ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:51:10+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:51:26+0000", "message":"Jolie poitrine mmm????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:51:31+0000", "message":"Merci ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:16+0000", "message":"Quel succ??s  ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:17+0000", "message":"Je prends le mauve , svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:29+0000", "message":"Prends 59"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:36+0000", "message":"C'est not??, le num??ro 59, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:46+0000", "message":"Je prends le 60"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:55+0000", "message":"C'est des brouteurs, ils sont tous un ?? c??t?? de l'autre ds une salle web ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:55+0000", "message":"On vous mets sur la liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:52:58+0000", "message":"Oui le 59. Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:53:03+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:53:15+0000", "message":"??poustouflante !"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:53:37+0000", "message":"Je prends le 60"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:53:43+0000", "message":"Je prends le 60 !"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:53:56+0000", "message":"Le 62 aussi, svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:54:04+0000", "message":"On vous mets sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:54:20+0000", "message":"On vous mets sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:54:34+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:54:43+0000", "message":"Prends 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:54:47+0000", "message":"Je pourrais revoir le 58 svp?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:54:48+0000", "message":"Le 100% mauve c'est quel num??ro ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:55:17+0000", "message":"Prends 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:55:18+0000", "message":"Je prends le 64 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:55:25+0000", "message":"Je prends le 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:55:42+0000", "message":"C'est le num??ro 59 :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:55:53+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:55:58+0000", "message":"Merci. Dispo?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:56:06+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:56:34+0000", "message":"Y aurait-il encore des foulards?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:56:46+0000", "message":"Que  reste il car voil?? que je rentre du travail merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:56:48+0000", "message":"J'aimerais  revoir le foulard arc en ciel"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:56:50+0000", "message":"Je prends 59 si d??sistement"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:00+0000", "message":"Pour moi 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:06+0000", "message":"Prends 65"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:07+0000", "message":"Je prend 65"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:12+0000", "message":"Oki merci. Bonne soir??e ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:32+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:35+0000", "message":"Je veux bien revoir 27 (je prends tr??s certainement) et 31"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:49+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:50+0000", "message":"Je veux bien revoir le 19,24,25,58.merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:57:55+0000", "message":"Bonsoir le 48 est-il tjrs dispo?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:03+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:12+0000", "message":"Je veux bien revoir le 26 et 27 merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:13+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:19+0000", "message":"Je voudrais revoir le 37"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T19:58:22+0000", "message":"Je peux revoir le 41 et 24 ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:30+0000", "message":"Bonsoir je veux bien le 65"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:33+0000", "message":"Oui il est disponible ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:52+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:58:53+0000", "message":"Quelle est la longueur des doubles tours svp?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T19:59:03+0000", "message":"Revoir 25 et 30 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:59:33+0000", "message":"Possible pour le revoir svp?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:59:47+0000", "message":"Cela d??pend des mod??les, n'h??sitez pas ?? venir les essayez en boutique ?? p??taouchnok :D"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T19:59:54+0000", "message":"Je prend ??galement le 25 si c est bien dans les tons beige ou ??cru ? C est bien le tissu original ? Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:00:19+0000", "message":"Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:00:30+0000", "message":"Oui c'est du beige/sable, c'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:00:39+0000", "message":"Ok je le prend le 25"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:01:13+0000", "message":"Je prends 72"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:01:27+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:02:23+0000", "message":"Je prends 75"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:02:37+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:02:47+0000", "message":"Bonsoir"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:03:27+0000", "message":"C'est du muncky le 74?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:03:33+0000", "message":"Prend 76"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:03:47+0000", "message":"C'est not??, merci :-"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:04:23+0000", "message":"Piotr, fait vivre le commerce de ta fille ! D??pense tes sous ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:04:38+0000", "message":"Je prends 73"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:04:46+0000", "message":"Je prends le 76"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:04:55+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:05:04+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:05:40+0000", "message":"Salut"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:05:47+0000", "message":"Je prends le 80"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:05:54+0000", "message":"Possible de revoir le 78 et le 80?  Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:04+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:10+0000", "message":"Je veux bien revoir le 65 pour ??tre s??r"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:13+0000", "message":"Je prends le 81, svp"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:06:14+0000", "message":"Je veux bien revoir 16 et 74"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:06:29+0000", "message":"Bonsoir. Est-ce que c'est possible de revoir les 10, 18 et 78 svp?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:31+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:39+0000", "message":"Je veux bien revoir le 19"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:40+0000", "message":"Je veux bien revoir le 33 svp ?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:06:40+0000", "message":"Peut-on revoir 33 et 46 si disponibles?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:44+0000", "message":"37"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:06:57+0000", "message":"Je veux bien revoir 58 et 68"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:06:57+0000", "message":"Si maman est d'accord je vais attendre les rescap??s????"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:06:57+0000", "message":"Possible de revoir le 46 et 47 ? Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:07:09+0000", "message":"Le 81, tu sais le refaire ou pas?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:07:14+0000", "message":"Je veux bien  revoir  le 6, 16, 31"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:07:29+0000", "message":"Revoir 27 (pour confirmer ) et 31 stp"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:07:42+0000", "message":"Revoir 37 et 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:07:44+0000", "message":"Non d??sol??e :/"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:07:50+0000", "message":"Je souhaite revoir les tours de cou ?? 15???"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:08:01+0000", "message":"Bonsoir... petite question ... pr??voyez vous de faire un live enfants un de ces jours ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:08:19+0000", "message":"Revoir le 48"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:08:27+0000", "message":"J?????tais absente lors du d??but, les promo sont encore valable demain ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:08:28+0000", "message":"Je veux bien revoir le 58 svp???"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:08:36+0000", "message":"Ton top te va ?? ravir !!! (Rien ?? voir avec le live, je sais ????)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:09:07+0000", "message":"Merci ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:09:15+0000", "message":"Oui demain lors des post des rescap??s :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:09:37+0000", "message":"Je prends le 78 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:09:54+0000", "message":"Le 65 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:02+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:23+0000", "message":"58?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:10:25+0000", "message":"37 et 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:29+0000", "message":"Vous le prenez? ou pour revoir? :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:38+0000", "message":"On peut revoir le 58?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:43+0000", "message":"J'essaie de rester sage... ????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:52+0000", "message":"Je prends le 82"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:10:53+0000", "message":"Coucou tu fais des triangles m??re fille? J ai loup?? le debut"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:11:37+0000", "message":"Pas sur ce live si, mais prochainement dans le semaine lorsque les nouveaux tissus arriveront????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:11:46+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:12:04+0000", "message":"Merci ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:12:27+0000", "message":"Je veux votre num??ro priv??"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:12:34+0000", "message":"Revoir le 16 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:12:37+0000", "message":"J???aimerais bien le num??ro 7 (je ne suis pas s??r du num??ro) il est avec des touches de oranger et de corail."}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:13:01+0000", "message":"Et je veux bien le revoir si c???est possible ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:13:10+0000", "message":"Il est pour toi le 16 Jenn!"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:13:11+0000", "message":"Ga??lle L??oDany tu craques pas ?! ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:13:26+0000", "message":"Je prends le 33"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:13:48+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:13:54+0000", "message":"Je prends le 18 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:05+0000", "message":"Allez le 16 pour moi ??????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:16+0000", "message":"C'est le num??ro 9"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:26+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:36+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:42+0000", "message":"J ai loupe le 19 ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:43+0000", "message":"Je prends ??galement le 46 si disponible??? merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:50+0000", "message":"Je peux revoir ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:14:52+0000", "message":"Je prends  le 22"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:15:07+0000", "message":"Bon Ga??lle craqu?? aussi, Marion fera bpost ????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:15:09+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:15:25+0000", "message":"Option sur le 24"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:15:39+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:15:38+0000", "message":"Allez Ga??lle craque aussi, Marion fera b post ????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:16:26+0000", "message":"Je confirme le 27 ??????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:16:34+0000", "message":"Si possible, option sur le 10; merci d'avance."}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:16:42+0000", "message":"Ok merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:16:53+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:17:18+0000", "message":"Je reste sur le 27, merci ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:17:29+0000", "message":"Est ce qu'il reste des doubles tours de cou fleuris?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:17:49+0000", "message":"J'aimais bcp de 74"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:17:49+0000", "message":"Pour moi le 37"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:17:56+0000", "message":"Possibilit?? de revoir le 33 si c???est possible de le refaire par hasard s???il vous pla??t ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:16+0000", "message":"Je viens d???arriver ????????????????? Que vous reste-t-il en triangle ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:20+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:31+0000", "message":"Est ce que vous prenez le 31 ? ?????????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:43+0000", "message":"Merci quand m??me ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:49+0000", "message":"Non juste le 27"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:53+0000", "message":"Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:18:56+0000", "message":"Quel tissu le 74?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:19:00+0000", "message":"Possible de revoir le 78?  Du moins, s'il y a ??ventuellement moyen de le refaire.  Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:19:27+0000", "message":"C'??tait un double tour fleuri aussi... Rose fushia sans minky"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:19:37+0000", "message":"C'est du jersey :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:19:46+0000", "message":"Je prends 37"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:19:51+0000", "message":"Fanny prends le 41?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:19:57+0000", "message":"Il y en a plein les foulards ????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:20:03+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:20:22+0000", "message":"??a marche! ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:20:31+0000", "message":"Ga??lle il est trop beau ??????????????????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:20:39+0000", "message":"Possibilit?? de revoir le 33 si c???est possible de le refaire par hasard s???il vous pla??t ?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:21:17+0000", "message":"J'attends de revoir le 58 et 68 ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:21:21+0000", "message":"??a va d'aller ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:06+0000", "message":"Il n'est plus disponible :/"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:36+0000", "message":"Je confirme les 3 choisis"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:43+0000", "message":"Je  prends 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:45+0000", "message":"65 je prends si dispo"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:47+0000", "message":"Je prends le 19.merci..."}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:50+0000", "message":"Possible de revoir le 65"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:55+0000", "message":"Je veux bien le 65"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:22:56+0000", "message":"Et 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:23:00+0000", "message":"Svp ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:23:07+0000", "message":"Non"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:23:35+0000", "message":"Est-ce que ce serait possible de revoir ensemble le 10 et le 18?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:23:38+0000", "message":"Le 12 41 et un tour de cou 63 je pense"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:23:39+0000", "message":"Vous pouvez remontrer les triangles ?? pompons si il y en a encore svp ?"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:23:41+0000", "message":"Je confirme le 37 et le 64"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:23:48+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:23:57+0000", "message":"Moi, je sais plus mes num??ros !!!"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:23:58+0000", "message":"Le 64 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:24:17+0000", "message":"On vous mets sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:24:20+0000", "message":"Et le 65 est prit aussi ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:24:27+0000", "message":"Est-ce possible de prendre le 27 ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:24:47+0000", "message":"On vous mets sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:25:01+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:25:10+0000", "message":"Pas 63 mais 72"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:25:13+0000", "message":"Bonsoir et merci pour le live. Ps: ma fille Elo??se adore le pull qui brille"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:25:22+0000", "message":"Ok pour le 65 ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:26:08+0000", "message":"Je prendrai le 24 alors merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:26:20+0000", "message":"Je peux revoir le 71 pour ??tre s??re ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:26:49+0000", "message":"Plus avec ce blanc l??"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:26:59+0000", "message":"Je peux revoir le 72"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:27:38+0000", "message":"Le 64 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:02+0000", "message":"On vous met sur la liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:09+0000", "message":"Je confirme le 18 ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:15+0000", "message":"Je peux revoir le 51"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:23+0000", "message":"Du coup j???ai pas tout compris c?????tait ok pour le 65 ? Ou liste d???attente ? ??????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:24+0000", "message":"Oui je confirme bien les 3 merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:49+0000", "message":"Oui c'est ok :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:28:51+0000", "message":"Je peux abuser et revoir le 19? Il est vert de chez vert ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:29:02+0000", "message":"J'ai loup?? le 48 possible de le remonter svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:29:03+0000", "message":"Pourrais-je revoir le 68 svp? S'il est disponible"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:29:07+0000", "message":"12 41 72 parfait"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:29:08+0000", "message":"Pour confirmation pourrais-tu remontrer le 76 s???il te pla??t ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:29:16+0000", "message":"Je prends le 19"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:29:27+0000", "message":"Est-ce que le 10 a des petites houppes aux extr??mit??s?  Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:30:17+0000", "message":"Je pourrais revoir le noir avec les fleur et le minky rose claire \nJe me souviens plus de num??ro d??sol??e"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:31:06+0000", "message":"Merci beaucoup.  Je prends donc le 10."}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:31:15+0000", "message":"Je prends le 51 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:31:21+0000", "message":"Moi si possible voir le 17 port?? ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:31:28+0000", "message":"Le 27 est-ce possible ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:31:33+0000", "message":"Le tissus du 51,il vous en reste ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:31:34+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:32:21+0000", "message":"Pour confirmation pourrais-tu remontrer le 76 s???il te pla??t ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:32:21+0000", "message":"Je prends le 51"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:32:23+0000", "message":"On vous met sur liste d'attente ;)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:33:14+0000", "message":"D???accord merci ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:33:18+0000", "message":"Je confirme le 49 ! ??????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:33:21+0000", "message":"Je t'envoie un mp alors pour le 51.merci.bonne soir??e.."}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:33:24+0000", "message":"Et des petits triangles Il y avait ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:33:31+0000", "message":"Je peux voir le 30 porte ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:33:43+0000", "message":"Est ce qu'il  reste  des  100 %"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:34:01+0000", "message":"Vous avez encore des foulards chaud ?? pompons svp ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:34:39+0000", "message":"Il est beau ce tissu rose kibrille"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:34:53+0000", "message":"Je suis sur liste d???attente alors ?"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:35:16+0000", "message":"Oui????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:35:31+0000", "message":"D???accord merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:36:19+0000", "message":"Je prends le 48 svp"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:36:18+0000", "message":"Il reste le 57 58 61 63"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:36:36+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME"id":"%IDUSER"},,  "created_time":"2022-03-23T20:37:03+0000", "message":"Je prends 65 et 30. Et liste d attente si d??sistement num??ro 19 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:37:16+0000", "message":"Je prends le 63"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:37:55+0000", "message":"Super ! :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:06+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:18+0000", "message":"Merci ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:22+0000", "message":"Bonne soir??e, je te mp demain ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:32+0000", "message":"Je prends aussi le 4 svp"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:55+0000", "message":"Comme celui que j???ai eu"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:55+0000", "message":"C'est not??, merci :)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:38:58+0000", "message":"Courage pour la fin... Je passerai voir les rescap??s demain matin ???? Bonne soir??e ?? vous et ?? bient??t ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:39:01+0000", "message":"Triangle avec attache :-)"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:39:58+0000", "message":"Merci"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:39:59+0000", "message":"Merci et bonne soir??e"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:00+0000", "message":"Pour le 65, vous ??tes aussi sur liste d'attente"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:01+0000", "message":"Merci beaucoup ! ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:05+0000", "message":"Merciiii, oui top ????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:07+0000", "message":"Merci ???? un live au top comme d???habitude dommage je suis arriv??e trop tard ??????"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:09+0000", "message":"Bonne soir??e, bonne nuit et merci pour le live"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:17+0000", "message":"Merci beaucoup"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:28+0000", "message":"Merci ?? toute l?????quipe??? bonne soir??e"}
    {"id":"%ID", "from":{"name":"%NAME", "id":"%IDUSER"},  "created_time":"2022-03-23T20:40:54+0000", "message":"Merci pour votre bonne humeur????"}
""".trimIndent()
    }
}

