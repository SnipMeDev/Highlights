// webpack.config.d/single-bundle.config.js
config.output = {
    filename: 'bundle.js',
    path: require('path').resolve(__dirname, 'dist'),
};