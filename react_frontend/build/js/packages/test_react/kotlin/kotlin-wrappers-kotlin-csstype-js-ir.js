(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', './kotlin-kotlin-stdlib-js-ir.js'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('./kotlin-kotlin-stdlib-js-ir.js'));
  else {
    if (typeof this['kotlin-kotlin-stdlib-js-ir'] === 'undefined') {
      throw new Error("Error loading module 'kotlin-wrappers-kotlin-csstype-js-ir'. Its dependency 'kotlin-kotlin-stdlib-js-ir' was not found. Please, check whether 'kotlin-kotlin-stdlib-js-ir' is loaded prior to 'kotlin-wrappers-kotlin-csstype-js-ir'.");
    }
    root['kotlin-wrappers-kotlin-csstype-js-ir'] = factory(typeof this['kotlin-wrappers-kotlin-csstype-js-ir'] === 'undefined' ? {} : this['kotlin-wrappers-kotlin-csstype-js-ir'], this['kotlin-kotlin-stdlib-js-ir']);
  }
}(this, function (_, kotlin_kotlin) {
  'use strict';
  //region block: imports
  var Unit_getInstance = kotlin_kotlin.$_$.e2;
  var interfaceMeta = kotlin_kotlin.$_$.p4;
  var THROW_CCE = kotlin_kotlin.$_$.m6;
  var Annotation = kotlin_kotlin.$_$.f6;
  var classMeta = kotlin_kotlin.$_$.i4;
  var toString = kotlin_kotlin.$_$.g5;
  //endregion
  //region block: pre-declaration
  function fontFace(block) {
    {
      var tmp$ret$1;
      {
        var tmp$ret$0;
        {
          tmp$ret$0 = this;
        }
        tmp$ret$1 = tmp$ret$0;
      }
      var tmp0_set = tmp$ret$1;
      var tmp$ret$4;
      {
        var tmp$ret$3;
        {
          var tmp$ret$2;
          {
            tmp$ret$2 = '@font-face';
          }
          tmp$ret$3 = tmp$ret$2;
        }
        tmp$ret$4 = tmp$ret$3;
      }
      var tmp1_set = tmp$ret$4;
      var tmp$ret$7;
      {
        var tmp$ret$6;
        {
          var tmp$ret$5;
          {
            tmp$ret$5 = {};
          }
          var tmp0_apply = tmp$ret$5;
          {
          }
          block(tmp0_apply);
          tmp$ret$6 = tmp0_apply;
        }
        tmp$ret$7 = tmp$ret$6;
      }
      var tmp2_set = tmp$ret$7;
      var tmp$ret$8;
      {
        tmp$ret$8 = tmp0_set;
      }
      tmp$ret$8[tmp1_set] = tmp2_set;
    }
  }
  function invoke(_this__u8e3s4, block) {
    {
      var tmp$ret$1;
      {
        var tmp$ret$0;
        {
          tmp$ret$0 = this;
        }
        tmp$ret$1 = tmp$ret$0;
      }
      var tmp0_set = tmp$ret$1;
      var tmp$ret$4;
      {
        var tmp$ret$3;
        {
          var tmp$ret$2;
          {
            tmp$ret$2 = {};
          }
          var tmp0_apply = tmp$ret$2;
          {
          }
          block(tmp0_apply);
          tmp$ret$3 = tmp0_apply;
        }
        tmp$ret$4 = tmp$ret$3;
      }
      var tmp1_set = tmp$ret$4;
      var tmp$ret$5;
      {
        tmp$ret$5 = tmp0_set;
      }
      tmp$ret$5[_this__u8e3s4] = tmp1_set;
    }
  }
  function invoke_0(_this__u8e3s4, block) {
    {
      var tmp$ret$2;
      {
        var tmp0_Selector = '.' + _this__u8e3s4;
        var tmp$ret$1;
        {
          var tmp$ret$0;
          {
            tmp$ret$0 = tmp0_Selector;
          }
          tmp$ret$1 = tmp$ret$0;
        }
        tmp$ret$2 = tmp$ret$1;
      }
      var tmp1_invoke = tmp$ret$2;
      {
        var tmp$ret$4;
        {
          var tmp$ret$3;
          {
            tmp$ret$3 = this;
          }
          tmp$ret$4 = tmp$ret$3;
        }
        var tmp0_set = tmp$ret$4;
        var tmp$ret$7;
        {
          var tmp$ret$6;
          {
            var tmp$ret$5;
            {
              tmp$ret$5 = {};
            }
            var tmp0_apply = tmp$ret$5;
            {
            }
            block(tmp0_apply);
            tmp$ret$6 = tmp0_apply;
          }
          tmp$ret$7 = tmp$ret$6;
        }
        var tmp1_set = tmp$ret$7;
        var tmp$ret$8;
        {
          tmp$ret$8 = tmp0_set;
        }
        tmp$ret$8[tmp1_invoke] = tmp1_set;
      }
    }
  }
  function invoke_1(_this__u8e3s4, block) {
    {
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
      var tmp0_invoke = tmp$ret$2;
      {
        var tmp$ret$4;
        {
          var tmp$ret$3;
          {
            tmp$ret$3 = this;
          }
          tmp$ret$4 = tmp$ret$3;
        }
        var tmp0_set = tmp$ret$4;
        var tmp$ret$7;
        {
          var tmp$ret$6;
          {
            var tmp$ret$5;
            {
              tmp$ret$5 = {};
            }
            var tmp0_apply = tmp$ret$5;
            {
            }
            block(tmp0_apply);
            tmp$ret$6 = tmp0_apply;
          }
          tmp$ret$7 = tmp$ret$6;
        }
        var tmp1_set = tmp$ret$7;
        var tmp$ret$8;
        {
          tmp$ret$8 = tmp0_set;
        }
        tmp$ret$8[tmp0_invoke] = tmp1_set;
      }
    }
  }
  function cue(selector, block) {
    {
      var tmp3_invoke = '::cue(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function cue_0(selector, block) {
    {
      var tmp3_invoke = '::cue(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function cueRegion(selector, block) {
    {
      var tmp3_invoke = '::cue-region(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function cueRegion_0(selector, block) {
    {
      var tmp3_invoke = '::cue-region(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function part(selector, block) {
    {
      var tmp3_invoke = '::part(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function part_0(selector, block) {
    {
      var tmp3_invoke = '::part(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function slotted(selector, block) {
    {
      var tmp3_invoke = '::slotted(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function slotted_0(selector, block) {
    {
      var tmp3_invoke = '::slotted(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function dir(selector, block) {
    {
      var tmp3_invoke = ':dir(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function dir_0(selector, block) {
    {
      var tmp3_invoke = ':dir(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function has(selector, block) {
    {
      var tmp3_invoke = ':has(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function has_0(selector, block) {
    {
      var tmp3_invoke = ':has(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function host(selector, block) {
    {
      var tmp3_invoke = ':host(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function host_0(selector, block) {
    {
      var tmp3_invoke = ':host(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function hostContext(selector, block) {
    {
      var tmp3_invoke = ':host-context(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function hostContext_0(selector, block) {
    {
      var tmp3_invoke = ':host-context(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function is(selector, block) {
    {
      var tmp3_invoke = ':is(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function is_0(selector, block) {
    {
      var tmp3_invoke = ':is(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function lang(selector, block) {
    {
      var tmp3_invoke = ':lang(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function lang_0(selector, block) {
    {
      var tmp3_invoke = ':lang(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function not(selector, block) {
    {
      var tmp3_invoke = ':not(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function not_0(selector, block) {
    {
      var tmp3_invoke = ':not(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthChild(selector, block) {
    {
      var tmp3_invoke = ':nth-child(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthChild_0(selector, block) {
    {
      var tmp3_invoke = ':nth-child(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthLastChild(selector, block) {
    {
      var tmp3_invoke = ':nth-last-child(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthLastChild_0(selector, block) {
    {
      var tmp3_invoke = ':nth-last-child(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthLastOfType(selector, block) {
    {
      var tmp3_invoke = ':nth-last-of-type(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthLastOfType_0(selector, block) {
    {
      var tmp3_invoke = ':nth-last-of-type(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthOfType(selector, block) {
    {
      var tmp3_invoke = ':nth-of-type(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthOfType_0(selector, block) {
    {
      var tmp3_invoke = ':nth-of-type(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function where(selector, block) {
    {
      var tmp3_invoke = ':where(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function where_0(selector, block) {
    {
      var tmp3_invoke = ':where(' + selector + ')';
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = tmp3_invoke;
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp2_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp2_invoke] = tmp1_set;
        }
      }
    }
  }
  function after(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::after';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function backdrop(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::backdrop';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function before(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::before';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function cue_1(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::cue';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function cueRegion_1(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::cue-region';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function firstLetter(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::first-letter';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function firstLine(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::first-line';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function grammarError(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::grammar-error';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function marker(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::marker';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function placeholder(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::placeholder';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function selection(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::selection';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function spellingError(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::spelling-error';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function targetText(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = '::target-text';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function active(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':active';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function anyLink(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':any-link';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function blank(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':blank';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function checked(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':checked';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function current(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':current';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function default_0(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':default';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function defined(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':defined';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function disabled(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':disabled';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function empty(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':empty';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function enabled(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':enabled';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function first(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':first';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function firstChild(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':first-child';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function firstOfType(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':first-of-type';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function focus(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':focus';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function focusVisible(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':focus-visible';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function focusWithin(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':focus-within';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function fullscreen(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':fullscreen';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function future(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':future';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function hover(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':hover';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function inRange(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':in-range';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function indeterminate(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':indeterminate';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function invalid(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':invalid';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function lastChild(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':last-child';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function lastOfType(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':last-of-type';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function left(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':left';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function link(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':link';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function localLink(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':local-link';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthCol(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':nth-col';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function nthLastCol(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':nth-last-col';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function onlyChild(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':only-child';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function onlyOfType(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':only-of-type';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function optional(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':optional';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function outOfRange(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':out-of-range';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function past(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':past';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function paused(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':paused';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function pictureInPicture(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':picture-in-picture';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function placeholderShown(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':placeholder-shown';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function readOnly(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':read-only';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function readWrite(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':read-write';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function required(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':required';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function right(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':right';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function root(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':root';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function scope(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':scope';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function target(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':target';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function targetWithin(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':target-within';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function userInvalid(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':user-invalid';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function userValid(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':user-valid';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function valid(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':valid';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  function visited(block) {
    {
      {
        var tmp$ret$2;
        {
          var tmp$ret$1;
          {
            var tmp$ret$0;
            {
              tmp$ret$0 = ':visited';
            }
            tmp$ret$1 = tmp$ret$0;
          }
          tmp$ret$2 = tmp$ret$1;
        }
        var tmp0_invoke = tmp$ret$2;
        {
          var tmp$ret$4;
          {
            var tmp$ret$3;
            {
              tmp$ret$3 = this;
            }
            tmp$ret$4 = tmp$ret$3;
          }
          var tmp0_set = tmp$ret$4;
          var tmp$ret$7;
          {
            var tmp$ret$6;
            {
              var tmp$ret$5;
              {
                tmp$ret$5 = {};
              }
              var tmp0_apply = tmp$ret$5;
              {
              }
              block(tmp0_apply);
              tmp$ret$6 = tmp0_apply;
            }
            tmp$ret$7 = tmp$ret$6;
          }
          var tmp1_set = tmp$ret$7;
          var tmp$ret$8;
          {
            tmp$ret$8 = tmp0_set;
          }
          tmp$ret$8[tmp0_invoke] = tmp1_set;
        }
      }
    }
  }
  //endregion
  function AdvancedPseudosRuleBuilder() {
  }
  AdvancedPseudosRuleBuilder.$metadata$ = interfaceMeta('AdvancedPseudosRuleBuilder', [RuleBuilder]);
  function rgb(red, green, blue) {
    var tmp$ret$1;
    {
      var tmp0_unsafeCast = 'rgb(' + red + ',' + green + ',' + blue + ')';
      var tmp$ret$0;
      {
        tmp$ret$0 = tmp0_unsafeCast;
      }
      tmp$ret$1 = tmp$ret$0;
    }
    return tmp$ret$1;
  }
  function CssDsl() {
  }
  CssDsl.prototype.equals = function (other) {
    if (!(other instanceof CssDsl))
      return false;
    var tmp0_other_with_cast = other instanceof CssDsl ? other : THROW_CCE();
    return true;
  };
  CssDsl.prototype.hashCode = function () {
    return 0;
  };
  CssDsl.prototype.toString = function () {
    return '@csstype.CssDsl()';
  };
  CssDsl.$metadata$ = classMeta('CssDsl', [Annotation]);
  function get_px(_this__u8e3s4) {
    var tmp$ret$1;
    {
      var tmp0_unsafeCast = toString(_this__u8e3s4) + 'px';
      var tmp$ret$0;
      {
        tmp$ret$0 = tmp0_unsafeCast;
      }
      tmp$ret$1 = tmp$ret$0;
    }
    return tmp$ret$1;
  }
  function get_pct(_this__u8e3s4) {
    var tmp$ret$1;
    {
      var tmp0_unsafeCast = toString(_this__u8e3s4) + '%';
      var tmp$ret$0;
      {
        tmp$ret$0 = tmp0_unsafeCast;
      }
      tmp$ret$1 = tmp$ret$0;
    }
    return tmp$ret$1;
  }
  function PseudosRuleBuilder() {
  }
  PseudosRuleBuilder.$metadata$ = interfaceMeta('PseudosRuleBuilder', [AdvancedPseudosRuleBuilder, SimplePseudosRuleBuilder]);
  function RuleBuilder() {
  }
  RuleBuilder.$metadata$ = interfaceMeta('RuleBuilder');
  function Selector(syntax) {
    var tmp$ret$1;
    {
      var tmp$ret$0;
      {
        tmp$ret$0 = syntax;
      }
      tmp$ret$1 = tmp$ret$0;
    }
    return tmp$ret$1;
  }
  function SimplePseudosRuleBuilder() {
  }
  SimplePseudosRuleBuilder.$metadata$ = interfaceMeta('SimplePseudosRuleBuilder', [RuleBuilder]);
  function PropertiesBuilder() {
  }
  PropertiesBuilder.$metadata$ = classMeta('PropertiesBuilder', [RuleBuilder, PseudosRuleBuilder]);
  //region block: post-declaration
  PropertiesBuilder.prototype.left_uihxws_k$ = left;
  PropertiesBuilder.prototype.right_e59s75_k$ = right;
  PropertiesBuilder.prototype.fontFace_63ca6m_k$ = fontFace;
  PropertiesBuilder.prototype.invoke_53g6ia_k$ = invoke;
  PropertiesBuilder.prototype.invoke_nni682_k$ = invoke_0;
  PropertiesBuilder.prototype.invoke_dnsoq2_k$ = invoke_1;
  PropertiesBuilder.prototype.cue_i7dwn5_k$ = cue;
  PropertiesBuilder.prototype.cue_w35pcn_k$ = cue_0;
  PropertiesBuilder.prototype.cue_hxf0mg_k$ = cue_1;
  PropertiesBuilder.prototype.cueRegion_snhyct_k$ = cueRegion;
  PropertiesBuilder.prototype.cueRegion_wivn1n_k$ = cueRegion_0;
  PropertiesBuilder.prototype.cueRegion_uj366s_k$ = cueRegion_1;
  PropertiesBuilder.prototype.part_iasl35_k$ = part;
  PropertiesBuilder.prototype.part_6fwztj_k$ = part_0;
  PropertiesBuilder.prototype.slotted_96tz6p_k$ = slotted;
  PropertiesBuilder.prototype.slotted_v0o69z_k$ = slotted_0;
  PropertiesBuilder.prototype.dir_2y0kgp_k$ = dir;
  PropertiesBuilder.prototype.dir_e6romp_k$ = dir_0;
  PropertiesBuilder.prototype.has_3jevie_k$ = has;
  PropertiesBuilder.prototype.has_tlcjwe_k$ = has_0;
  PropertiesBuilder.prototype.host_97drqk_k$ = host;
  PropertiesBuilder.prototype.host_y8s88k_k$ = host_0;
  PropertiesBuilder.prototype.hostContext_p9odv_k$ = hostContext;
  PropertiesBuilder.prototype.hostContext_yfbi5x_k$ = hostContext_0;
  PropertiesBuilder.prototype.is_j1bckq_k$ = is;
  PropertiesBuilder.prototype.is_fkc90y_k$ = is_0;
  PropertiesBuilder.prototype.lang_o2lgwq_k$ = lang;
  PropertiesBuilder.prototype.lang_6agh76_k$ = lang_0;
  PropertiesBuilder.prototype.not_3hqvvl_k$ = not;
  PropertiesBuilder.prototype.not_77smd3_k$ = not_0;
  PropertiesBuilder.prototype.nthChild_vexhs6_k$ = nthChild;
  PropertiesBuilder.prototype.nthChild_9e03cy_k$ = nthChild_0;
  PropertiesBuilder.prototype.nthLastChild_jannkg_k$ = nthLastChild;
  PropertiesBuilder.prototype.nthLastChild_l1s688_k$ = nthLastChild_0;
  PropertiesBuilder.prototype.nthLastOfType_97kc5n_k$ = nthLastOfType;
  PropertiesBuilder.prototype.nthLastOfType_33qifh_k$ = nthLastOfType_0;
  PropertiesBuilder.prototype.nthOfType_bbcem7_k$ = nthOfType;
  PropertiesBuilder.prototype.nthOfType_h35149_k$ = nthOfType_0;
  PropertiesBuilder.prototype.where_qtas0d_k$ = where;
  PropertiesBuilder.prototype.where_9d2nqt_k$ = where_0;
  PropertiesBuilder.prototype.after_865zcv_k$ = after;
  PropertiesBuilder.prototype.backdrop_crgsaj_k$ = backdrop;
  PropertiesBuilder.prototype.before_4euk4k_k$ = before;
  PropertiesBuilder.prototype.firstLetter_d4j2g5_k$ = firstLetter;
  PropertiesBuilder.prototype.firstLine_4z1aon_k$ = firstLine;
  PropertiesBuilder.prototype.grammarError_v7pmka_k$ = grammarError;
  PropertiesBuilder.prototype.marker_fy5pep_k$ = marker;
  PropertiesBuilder.prototype.placeholder_b14f54_k$ = placeholder;
  PropertiesBuilder.prototype.selection_9mq1dd_k$ = selection;
  PropertiesBuilder.prototype.spellingError_vvnir1_k$ = spellingError;
  PropertiesBuilder.prototype.targetText_mk8vy5_k$ = targetText;
  PropertiesBuilder.prototype.active_vgztat_k$ = active;
  PropertiesBuilder.prototype.anyLink_3begid_k$ = anyLink;
  PropertiesBuilder.prototype.blank_hb2nt_k$ = blank;
  PropertiesBuilder.prototype.checked_emg2us_k$ = checked;
  PropertiesBuilder.prototype.current_9sx6ea_k$ = current;
  PropertiesBuilder.prototype.default_4ayi3q_k$ = default_0;
  PropertiesBuilder.prototype.defined_4s7566_k$ = defined;
  PropertiesBuilder.prototype.disabled_gruzxd_k$ = disabled;
  PropertiesBuilder.prototype.empty_v1xgz6_k$ = empty;
  PropertiesBuilder.prototype.enabled_rx2key_k$ = enabled;
  PropertiesBuilder.prototype.first_9klqwb_k$ = first;
  PropertiesBuilder.prototype.firstChild_5hltjj_k$ = firstChild;
  PropertiesBuilder.prototype.firstOfType_nj5k0q_k$ = firstOfType;
  PropertiesBuilder.prototype.focus_93hpab_k$ = focus;
  PropertiesBuilder.prototype.focusVisible_m9az6p_k$ = focusVisible;
  PropertiesBuilder.prototype.focusWithin_klnyrc_k$ = focusWithin;
  PropertiesBuilder.prototype.fullscreen_d9rc80_k$ = fullscreen;
  PropertiesBuilder.prototype.future_rhy754_k$ = future;
  PropertiesBuilder.prototype.hover_833p3z_k$ = hover;
  PropertiesBuilder.prototype.inRange_f304cd_k$ = inRange;
  PropertiesBuilder.prototype.indeterminate_8bi1sw_k$ = indeterminate;
  PropertiesBuilder.prototype.invalid_uvwpuk_k$ = invalid;
  PropertiesBuilder.prototype.lastChild_9eelfv_k$ = lastChild;
  PropertiesBuilder.prototype.lastOfType_buj0lo_k$ = lastOfType;
  PropertiesBuilder.prototype.link_3rymlb_k$ = link;
  PropertiesBuilder.prototype.localLink_b6wb1m_k$ = localLink;
  PropertiesBuilder.prototype.nthCol_x2iug3_k$ = nthCol;
  PropertiesBuilder.prototype.nthLastCol_5rhvzh_k$ = nthLastCol;
  PropertiesBuilder.prototype.onlyChild_zzool_k$ = onlyChild;
  PropertiesBuilder.prototype.onlyOfType_zgz0ym_k$ = onlyOfType;
  PropertiesBuilder.prototype.optional_2a56j_k$ = optional;
  PropertiesBuilder.prototype.outOfRange_1y0w2l_k$ = outOfRange;
  PropertiesBuilder.prototype.past_pqy67r_k$ = past;
  PropertiesBuilder.prototype.paused_hf9qel_k$ = paused;
  PropertiesBuilder.prototype.pictureInPicture_kh7dy8_k$ = pictureInPicture;
  PropertiesBuilder.prototype.placeholderShown_37lcg3_k$ = placeholderShown;
  PropertiesBuilder.prototype.readOnly_c5hyyv_k$ = readOnly;
  PropertiesBuilder.prototype.readWrite_9b9xde_k$ = readWrite;
  PropertiesBuilder.prototype.required_mxoi9w_k$ = required;
  PropertiesBuilder.prototype.root_pogsop_k$ = root;
  PropertiesBuilder.prototype.scope_q06gc9_k$ = scope;
  PropertiesBuilder.prototype.target_ukhchi_k$ = target;
  PropertiesBuilder.prototype.targetWithin_us6r7l_k$ = targetWithin;
  PropertiesBuilder.prototype.userInvalid_tz53zj_k$ = userInvalid;
  PropertiesBuilder.prototype.userValid_imrb2u_k$ = userValid;
  PropertiesBuilder.prototype.valid_iucyxt_k$ = valid;
  PropertiesBuilder.prototype.visited_9o045r_k$ = visited;
  //endregion
  return _;
}));

//# sourceMappingURL=kotlin-wrappers-kotlin-csstype-js-ir.js.map