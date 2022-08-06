(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', './kotlin-kotlin-stdlib-js-ir.js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('./kotlin-kotlin-stdlib-js-ir.js'));
  else {
    if (typeof this['kotlin-kotlin-stdlib-js-ir'] === 'undefined') {
      throw new Error("Error loading module 'kotlin-wrappers-kotlin-extensions-js-ir'. Its dependency 'kotlin-kotlin-stdlib-js-ir' was not found. Please, check whether 'kotlin-kotlin-stdlib-js-ir' is loaded prior to 'kotlin-wrappers-kotlin-extensions-js-ir'.");
    }
    root['kotlin-wrappers-kotlin-extensions-js-ir'] = factory(typeof this['kotlin-wrappers-kotlin-extensions-js-ir'] === 'undefined' ? {} : this['kotlin-wrappers-kotlin-extensions-js-ir'], this['kotlin-kotlin-stdlib-js-ir']);
  }
}(this, function (_, kotlin_kotlin) {
  'use strict';
  //region block: imports
  var Unit_getInstance = kotlin_kotlin.$_$.e2;
  var classMeta = kotlin_kotlin.$_$.i4;
  var interfaceMeta = kotlin_kotlin.$_$.p4;
  //endregion
  //region block: pre-declaration
  function get(key) {
    var tmp$ret$0;
    {
      tmp$ret$0 = this;
    }
    return tmp$ret$0[key];
  }
  function set(key, value) {
    var tmp$ret$0;
    {
      tmp$ret$0 = this;
    }
    tmp$ret$0[key] = value;
  }
  //endregion
  function JsPair() {
  }
  JsPair.prototype.component1_7eebsc_k$ = function () {
    var tmp$ret$0;
    {
      tmp$ret$0 = this;
    }
    return tmp$ret$0[0];
  };
  JsPair.prototype.component2_7eebsb_k$ = function () {
    var tmp$ret$0;
    {
      tmp$ret$0 = this;
    }
    return tmp$ret$0[1];
  };
  JsPair.$metadata$ = classMeta('JsPair');
  function Record() {
  }
  Record.$metadata$ = interfaceMeta('Record');
  function jso(block) {
    var tmp$ret$1;
    {
      var tmp$ret$0;
      {
        tmp$ret$0 = {};
      }
      var tmp0_apply = tmp$ret$0;
      {
      }
      block(tmp0_apply);
      tmp$ret$1 = tmp0_apply;
    }
    return tmp$ret$1;
  }
  function jso_0() {
    return {};
  }
  return _;
}));

//# sourceMappingURL=kotlin-wrappers-kotlin-extensions-js-ir.js.map