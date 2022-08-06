(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'react', './kotlin-kotlin-stdlib-js-ir.js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('react'), require('./kotlin-kotlin-stdlib-js-ir.js'));
  else {
    if (typeof react === 'undefined') {
      throw new Error("Error loading module 'kotlin-wrappers-kotlin-react-js-ir'. Its dependency 'react' was not found. Please, check whether 'react' is loaded prior to 'kotlin-wrappers-kotlin-react-js-ir'.");
    }
    if (typeof this['kotlin-kotlin-stdlib-js-ir'] === 'undefined') {
      throw new Error("Error loading module 'kotlin-wrappers-kotlin-react-js-ir'. Its dependency 'kotlin-kotlin-stdlib-js-ir' was not found. Please, check whether 'kotlin-kotlin-stdlib-js-ir' is loaded prior to 'kotlin-wrappers-kotlin-react-js-ir'.");
    }
    root['kotlin-wrappers-kotlin-react-js-ir'] = factory(typeof this['kotlin-wrappers-kotlin-react-js-ir'] === 'undefined' ? {} : this['kotlin-wrappers-kotlin-react-js-ir'], react, this['kotlin-kotlin-stdlib-js-ir']);
  }
}(this, function (_, $module$react, kotlin_kotlin) {
  'use strict';
  //region block: imports
  var createElement = $module$react.createElement;
  var Children = $module$react.Children;
  var isValidElement = $module$react.isValidElement;
  var Fragment = $module$react.Fragment;
  var Unit_getInstance = kotlin_kotlin.$_$.e2;
  var interfaceMeta = kotlin_kotlin.$_$.p4;
  var classMeta = kotlin_kotlin.$_$.i4;
  var single = kotlin_kotlin.$_$.q3;
  //endregion
  //region block: pre-declaration
  function unaryPlus(_this__u8e3s4) {
    Object.assign(this, _this__u8e3s4);
  }
  function child(element) {
    var tmp$ret$1;
    {
      var tmp$ret$0;
      {
        tmp$ret$0 = this;
      }
      tmp$ret$1 = tmp$ret$0[get_CHILDREN()];
    }
    if (!(tmp$ret$1 == null)) {
      var tmp$ret$4;
      {
        var tmp$ret$3;
        {
          var tmp$ret$2;
          {
            tmp$ret$2 = this;
          }
          tmp$ret$3 = tmp$ret$2[get_CHILDREN()];
        }
        var tmp0_asDynamic = tmp$ret$3;
        tmp$ret$4 = tmp0_asDynamic;
      }
      tmp$ret$4.push(element);
    } else {
      {
        var tmp$ret$7;
        {
          var tmp$ret$6;
          {
            var tmp$ret$5;
            {
              tmp$ret$5 = [element];
            }
            tmp$ret$6 = tmp$ret$5;
          }
          tmp$ret$7 = tmp$ret$6;
        }
        var tmp1__set_children__edo2ot = tmp$ret$7;
        var tmp$ret$8;
        {
          tmp$ret$8 = this;
        }
        tmp$ret$8[get_CHILDREN()] = tmp1__set_children__edo2ot;
      }
    }
  }
  function unaryPlus_0(_this__u8e3s4) {
    this.child_l8x7rz_k$(_this__u8e3s4);
  }
  function unaryPlus_1(_this__u8e3s4) {
    var tmp$ret$2;
    {
      var tmp$ret$1;
      {
        var tmp$ret$0;
        {
          tmp$ret$0 = _this__u8e3s4;
        }
        tmp$ret$1 = tmp$ret$0;
      }
      tmp$ret$2 = tmp$ret$1;
    }
    this.unaryPlus_remv6s_k$(tmp$ret$2);
  }
  function child_0(type, props) {
    this.unaryPlus_remv6s_k$(createElement(type, props));
  }
  function invoke(_this__u8e3s4) {
    this.unaryPlus_remv6s_k$(createElement(_this__u8e3s4));
  }
  function invoke_0(_this__u8e3s4, block) {
    this.unaryPlus_remv6s_k$(create(_this__u8e3s4, block));
  }
  function invoke_1(_this__u8e3s4, value, block) {
    this.invoke_bsyc51_k$(_this__u8e3s4, ChildrenBuilder$invoke$lambda(value, block));
  }
  function children(_this__u8e3s4) {
    {
      var tmp0_forEach = Children.toArray(_this__u8e3s4.children);
      var indexedObject = tmp0_forEach;
      var inductionVariable = 0;
      var last = indexedObject.length;
      while (inductionVariable < last) {
        var element = indexedObject[inductionVariable];
        inductionVariable = inductionVariable + 1 | 0;
        this.child_l8x7rz_k$(element);
      }
    }
  }
  //endregion
  function get_CHILDREN() {
    init_properties_ChildrenBuilder_kt_7yrkko();
    return CHILDREN;
  }
  var CHILDREN;
  function ChildrenBuilder$invoke$lambda($value, $block) {
    return function ($this$invoke) {
      $this$invoke.value = $value;
      $block($this$invoke);
      return Unit_getInstance();
    };
  }
  function ChildrenBuilder() {
  }
  ChildrenBuilder.$metadata$ = interfaceMeta('ChildrenBuilder');
  function createChildrenBuilder() {
    init_properties_ChildrenBuilder_kt_7yrkko();
    return new ChildrenBuilderImpl();
  }
  function set_children(_this__u8e3s4, value) {
    init_properties_ChildrenBuilder_kt_7yrkko();
    var tmp$ret$0;
    {
      tmp$ret$0 = _this__u8e3s4;
    }
    tmp$ret$0[get_CHILDREN()] = value;
  }
  function get_children(_this__u8e3s4) {
    init_properties_ChildrenBuilder_kt_7yrkko();
    var tmp$ret$0;
    {
      tmp$ret$0 = _this__u8e3s4;
    }
    return tmp$ret$0[get_CHILDREN()];
  }
  function ChildrenBuilderImpl() {
  }
  ChildrenBuilderImpl.$metadata$ = classMeta('ChildrenBuilderImpl', [ChildrenBuilder]);
  var properties_initialized_ChildrenBuilder_kt_gby2z0;
  function init_properties_ChildrenBuilder_kt_7yrkko() {
    if (properties_initialized_ChildrenBuilder_kt_gby2z0) {
    } else {
      properties_initialized_ChildrenBuilder_kt_gby2z0 = true;
      CHILDREN = Symbol('@@children');
    }
  }
  function FC(block) {
    var component = FC$lambda(block);
    var tmp$ret$1;
    {
      var tmp$ret$0;
      {
        tmp$ret$0 = component;
      }
      tmp$ret$1 = tmp$ret$0;
    }
    return tmp$ret$1;
  }
  function FC$lambda$lambda($block, $props) {
    return function ($this$createElementOrNull) {
      $block($this$createElementOrNull, $props);
      return Unit_getInstance();
    };
  }
  function FC$lambda($block) {
    return function (props) {
      return createElementOrNull(FC$lambda$lambda($block, props));
    };
  }
  function create(_this__u8e3s4, block) {
    var builder = createChildrenBuilder();
    var tmp$ret$2;
    {
      var tmp$ret$1;
      {
        var tmp$ret$0;
        {
          tmp$ret$0 = builder;
        }
        tmp$ret$1 = tmp$ret$0;
      }
      var tmp0_apply = tmp$ret$1;
      {
      }
      block(tmp0_apply);
      tmp$ret$2 = tmp0_apply;
    }
    var props = tmp$ret$2;
    var tmp$ret$4;
    {
      var tmp$ret$3;
      {
        tmp$ret$3 = builder;
      }
      tmp$ret$4 = tmp$ret$3[get_CHILDREN()];
    }
    var tmp0_elvis_lhs = tmp$ret$4;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      var tmp$ret$5;
      {
        tmp$ret$5 = [];
      }
      tmp = tmp$ret$5;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    var children = tmp;
    return createElement.apply(null, [_this__u8e3s4, props].concat([].slice.call(children.slice())));
  }
  function createElementOrNull(block) {
    var tmp$ret$2;
    {
      var tmp$ret$0;
      {
        var tmp0_apply = createChildrenBuilder();
        {
        }
        block(tmp0_apply);
        tmp$ret$0 = tmp0_apply;
      }
      var tmp1__get_children__1krr81 = tmp$ret$0;
      var tmp$ret$1;
      {
        tmp$ret$1 = tmp1__get_children__1krr81;
      }
      tmp$ret$2 = tmp$ret$1[get_CHILDREN()];
    }
    var tmp0_elvis_lhs = tmp$ret$2;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      return null;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    var children = tmp;
    var tmp$ret$3;
    {
      tmp$ret$3 = children.length === 0;
    }
    if (tmp$ret$3)
      return null;
    if (children.length === 1) {
      var tmp1_elvis_lhs = single(children);
      var tmp_0;
      if (tmp1_elvis_lhs == null) {
        return null;
      } else {
        tmp_0 = tmp1_elvis_lhs;
      }
      var child = tmp_0;
      if (isValidElement(child)) {
        var tmp$ret$5;
        {
          var tmp$ret$4;
          {
            tmp$ret$4 = child;
          }
          tmp$ret$5 = tmp$ret$4;
        }
        return tmp$ret$5;
      }
    }
    return createElement.apply(null, [Fragment, void 1].concat([].slice.call(children.slice())));
  }
  //region block: post-declaration
  ChildrenBuilderImpl.prototype.unaryPlus_hmv8mt_k$ = unaryPlus;
  ChildrenBuilderImpl.prototype.unaryPlus_remv6s_k$ = unaryPlus_0;
  ChildrenBuilderImpl.prototype.unaryPlus_g7ydph_k$ = unaryPlus_1;
  ChildrenBuilderImpl.prototype.child_l8x7rz_k$ = child;
  ChildrenBuilderImpl.prototype.child_3jou7y_k$ = child_0;
  ChildrenBuilderImpl.prototype.invoke_137ebf_k$ = invoke;
  ChildrenBuilderImpl.prototype.invoke_bsyc51_k$ = invoke_0;
  ChildrenBuilderImpl.prototype.invoke_q7gl1a_k$ = invoke_1;
  ChildrenBuilderImpl.prototype.children_v4df_k$ = children;
  //endregion
  //region block: exports
  _.$_$ = _.$_$ || {};
  _.$_$.a = FC;
  _.$_$.b = create;
  //endregion
  return _;
}));

//# sourceMappingURL=kotlin-wrappers-kotlin-react-js-ir.js.map