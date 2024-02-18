const { merge } = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    devtool: 'inline-source-map',
    devServer: {
        static: './public/',
        port: 3000,
        client: {
            webSocketURL: 'ws://localhost:8080/ws',
        },
        allowedHosts: "all"
    },
    optimization: {
        runtimeChunk: 'single',
    },
})