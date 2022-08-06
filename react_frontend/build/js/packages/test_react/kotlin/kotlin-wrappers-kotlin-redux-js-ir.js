(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', './kotlin-kotlin-stdlib-js-ir.js', './kotlinx-serialization-kotlinx-serialization-json-js-ir.js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('./kotlin-kotlin-stdlib-js-ir.js'), require('./kotlinx-serialization-kotlinx-serialization-json-js-ir.js'));
  else {
    if (typeof this['kotlin-kotlin-stdlib-js-ir'] === 'undefined') {
      throw new Error("Error loading module 'kotlin-wrappers-kotlin-redux-js-ir'. Its dependency 'kotlin-kotlin-stdlib-js-ir' was not found. Please, check whether 'kotlin-kotlin-stdlib-js-ir' is loaded prior to 'kotlin-wrappers-kotlin-redux-js-ir'.");
    }
    if (typeof this['kotlinx-serialization-kotlinx-serialization-json-js-ir'] === 'undefined') {
      throw new Error("Error loading module 'kotlin-wrappers-kotlin-redux-js-ir'. Its dependency 'kotlinx-serialization-kotlinx-serialization-json-js-ir' was not found. Please, check whether 'kotlinx-serialization-kotlinx-serialization-json-js-ir' is loaded prior to 'kotlin-wrappers-kotlin-redux-js-ir'.");
    }
    root['kotlin-wrappers-kotlin-redux-js-ir'] = factory(typeof this['kotlin-wrappers-kotlin-redux-js-ir'] === 'undefined' ? {} : this['kotlin-wrappers-kotlin-redux-js-ir'], this['kotlin-kotlin-stdlib-js-ir'], this['kotlinx-serialization-kotlinx-serialization-json-js-ir']);
  }
}(this, function (_, kotlin_kotlin, kotlin_org_jetbrains_kotlinx_kotlinx_serialization_json) {
  'use strict';
  //region block: imports
  var Unit_getInstance = kotlin_kotlin.$_$.e2;
  var Json$default = kotlin_org_jetbrains_kotlinx_kotlinx_serialization_json.$_$.a;
  //endregion
  //region block: pre-declaration
  //endregion
  function get_defaultJson() {
    init_properties_Serialization_kt_wn61l0();
    return defaultJson;
  }
  var defaultJson;
  function defaultJson$lambda() {
    return function ($this$Json) {
      $this$Json.isLenient_1 = true;
      $this$Json.ignoreUnknownKeys_1 = true;
      $this$Json.allowSpecialFloatingPointValues_1 = true;
      $this$Json.useArrayPolymorphism_1 = true;
      return Unit_getInstance();
    };
  }
  var properties_initialized_Serialization_kt_xannds;
  function init_properties_Serialization_kt_wn61l0() {
    if (properties_initialized_Serialization_kt_xannds) {
    } else {
      properties_initialized_Serialization_kt_xannds = true;
      defaultJson = Json$default(null, defaultJson$lambda(), 1, null);
    }
  }
  return _;
}));

//# sourceMappingURL=kotlin-wrappers-kotlin-redux-js-ir.js.map