const fs = require('fs')
const HtmlWebpackPlugin = require('html-webpack-plugin');

const codeFileRegexp = /((.*\/)*(.+))\.[jt]sx?$/

const [entry, htmlPlugins] = fs.readdirSync('./pages', {recursive: true})
    .reduce(([e, h], filePath) => {
        const result = codeFileRegexp.exec(filePath)
        if (result != null) {
            e[result[1]] = './pages/' + filePath
            h.push(new HtmlWebpackPlugin({
                title:result[3],
                chunks: [result[1]],
                filename: result[1] + '.html',
                template: './lib/index.html'
            }))
        }
        return [e, h]
    }, [{}, []])

module.exports = {
    name: 'pages',
    entry,
    output: {
        filename: '[name].[contenthash].js',
        path: __dirname + '/dist',
    },
    module: {
        rules: [
            { test: /\.tsx?$/, use: 'ts-loader', exclude: /node_modules/ },
            { test: /\.(png|svg|jpg|jpeg|gif|webp)$/i, type: 'asset/resource' },
        ],
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.jsx', '.js'],
    },
    plugins: [
        ...htmlPlugins
    ],
};