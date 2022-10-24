const webpack = require("webpack")

const definePlugin = new webpack.DefinePlugin(
    {
        ENV_WEBPACK_MODE: JSON.stringify(config.mode)
    }
)

config.plugins.push(definePlugin)
