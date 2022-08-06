{
  mode: 'development',
  resolve: {
    modules: [
      'node_modules'
    ]
  },
  plugins: [
    TeamCityErrorPlugin {}
  ],
  module: {
    rules: [
      {
        test: /\.js$/,
        use: [
          'source-map-loader'
        ],
        enforce: 'pre'
      },
      {
        test: /\.css$/,
        use: [
          {
            loader: 'style-loader',
            options: {}
          },
          {
            loader: 'css-loader',
            options: {}
          }
        ]
      }
    ]
  },
  entry: {
    main: [
      '/Users/alexandregenon/workspace/test_react/build/js/packages/test_react/kotlin/test_react.js'
    ]
  },
  output: {
    path: '/Users/alexandregenon/workspace/test_react/build/distributions',
    filename: [Function: filename],
    library: 'test_react',
    libraryTarget: 'umd',
    globalObject: 'this'
  },
  devtool: 'eval-source-map',
  ignoreWarnings: [
    /Failed to parse source map/
  ],
  devServer: {
    open: true,
    static: [
      '/Users/alexandregenon/workspace/test_react/build/processedResources/js/main'
    ]
  },
  stats: {
    warnings: false,
    errors: false
  }
}