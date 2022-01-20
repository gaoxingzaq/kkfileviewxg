(function(t) {
	function e(e) {
		for (var n, s, c = e[0], r = e[1], l = e[2], u = 0, f = []; u < c.length; u++) s = c[u], Object.prototype.hasOwnProperty.call(i, s) && i[s] && f.push(i[s][0]), i[s] = 0;
		for (n in r) Object.prototype.hasOwnProperty.call(r, n) && (t[n] = r[n]);
		d && d(e);
		while (f.length) f.shift()();
		return o.push.apply(o, l || []), a()
	}

	function a() {
		for (var t, e = 0; e < o.length; e++) {
			for (var a = o[e], n = !0, c = 1; c < a.length; c++) {
				var r = a[c];
				0 !== i[r] && (n = !1)
			}
			n && (o.splice(e--, 1), t = s(s.s = a[0]))
		}
		return t
	}
	var n = {},
		i = {
			app: 0
		},
		o = [];

	function s(e) {
		if (n[e]) return n[e].exports;
		var a = n[e] = {
			i: e,
			l: !1,
			exports: {}
		};
		return t[e].call(a.exports, a, a.exports, s), a.l = !0, a.exports
	}
	s.m = t, s.c = n, s.d = function(t, e, a) {
		s.o(t, e) || Object.defineProperty(t, e, {
			enumerable: !0,
			get: a
		})
	}, s.r = function(t) {
		"undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {
			value: "Module"
		}), Object.defineProperty(t, "__esModule", {
			value: !0
		})
	}, s.t = function(t, e) {
		if (1 & e && (t = s(t)), 8 & e) return t;
		if (4 & e && "object" === typeof t && t && t.__esModule) return t;
		var a = Object.create(null);
		if (s.r(a), Object.defineProperty(a, "default", {
			enumerable: !0,
			value: t
		}), 2 & e && "string" != typeof t)
			for (var n in t) s.d(a, n, function(e) {
				return t[e]
			}.bind(null, n));
		return a
	}, s.n = function(t) {
		var e = t && t.__esModule ? function() {
			return t["default"]
		} : function() {
			return t
		};
		return s.d(e, "a", e), e
	}, s.o = function(t, e) {
		return Object.prototype.hasOwnProperty.call(t, e)
	}, s.p = "/ofd/";
	var c = window["webpackJsonp"] = window["webpackJsonp"] || [],
		r = c.push.bind(c);
	c.push = e, c = c.slice();
	for (var l = 0; l < c.length; l++) e(c[l]);
	var d = r;
	o.push([0, "chunk-vendors"]), a()
})({
	0: function(t, e, a) {
		t.exports = a("56d7")
	},
	"034f": function(t, e, a) {
		"use strict";
		a("85ec")
	},
	"56d7": function(t, e, a) {
		"use strict";
		a.r(e);
		a("e260"), a("e6cf"), a("cca6"), a("a79d");
		var n, i = a("2b0e"),
			o = function() {
				var t = this,
					e = t.$createElement,
					a = t._self._c || e;
				return a("div", {
					attrs: {
						id: "app"
					}
				}, [a("HelloWorld")], 1)
			},
			s = [],
			c = function() {
				var t = this,
					e = t.$createElement,
					a = t._self._c || e;
				return a("el-container", {
					staticStyle: {
						width: "100vw",
						height: "100vh"
					}
				}, [a("el-header", {
					staticStyle: {
						background: "#F5F5F5",
						display: "flex",
						height: "40px",
						border: "1px solid #e8e8e8",
						"align-items": "center"
					}
				}, [a("div", {
					staticClass: "upload-icon",
					on: {
						click: t.uploadFile
					}
				}, [a("div", {
					staticClass: "upload-icon"
				}, [t._v("打开OFD")]), a("input", {
					ref: "file",
					staticClass: "hidden",
					attrs: {
						type: "file",
						accept: ".ofd"
					},
					on: {
						change: t.fileChanged
					}
				})]), t.dragseal ? a("div", {
					staticClass: "upload-icon",
					on: {
						click: t.getSealPosition
					}
				}, [a("div", {
					staticClass: "upload-icon"
				}, [t._v("获取签章坐标")])]) : t._e(), a("div", {
					staticClass: "upload-icon",
					on: {
						click: t.print
					}
				}, [a("div", {
					staticClass: "upload-icon"
				}, [t._v("打印")])]), a("div", {
					staticClass: "upload-icon",
					on: {
						click: t.printSecret
					}
				}, [a("div", {
					staticClass: "upload-icon"
				}, [t._v("脱密打印")])]), t.ofdObj ? a("div", {
					staticStyle: {
						display: "flex",
						"align-items": "center"
					}
				}, [a("div", {
					staticClass: "upload-icon",
					staticStyle: {
						"margin-left": "10px"
					},
					on: {
						click: t.downPdf
					}
				}, [t._v(" 下载Ofd ")]), a("div", {
					staticClass: "scale-icon",
					staticStyle: {
						"margin-left": "10px"
					},
					on: {
						click: t.plus
					}
				}, [a("i", {
					staticClass: "el-icon-circle-plus-outline"
				})]), a("div", {
					staticClass: "scale-icon",
					on: {
						click: t.minus
					}
				}, [a("i", {
					staticClass: "el-icon-remove-outline"
				})]), a("div", {
					staticClass: "scale-icon"
				}, [a("font-awesome-icon", {
					attrs: {
						icon: "step-backward"
					},
					on: {
						click: t.firstPage
					}
				})], 1), a("div", {
					staticClass: "scale-icon",
					staticStyle: {
						"font-size": "18px"
					},
					on: {
						click: t.prePage
					}
				}, [a("i", {
					staticClass: "el-icon-caret-left"
				})]), a("div", {
					staticClass: "scale-icon"
				}, [t._v(" " + t._s(t.pageIndex) + "/" + t._s(t.pageCount) + " ")]), a("div", {
					staticClass: "scale-icon",
					staticStyle: {
						"font-size": "18px"
					},
					on: {
						click: t.nextPage
					}
				}, [a("i", {
					staticClass: "el-icon-caret-right"
				})]), a("div", {
					staticClass: "scale-icon",
					on: {
						click: t.lastPage
					}
				}, [a("font-awesome-icon", {
					attrs: {
						icon: "step-forward"
					}
				})], 1)]) : t._e()]), a("el-main", {
					directives: [{
						name: "loading",
						rawName: "v-loading",
						value: t.loading,
						expression: "loading"
					}],
					staticStyle: {
						height: "auto",
						background: "#808080",
						padding: "0"
					},
					attrs: {
						id: "main"
					}
				}, [a("div", {
					ref: "contentDiv",
					staticClass: "main-section",
					attrs: {
						id: "content"
					},
					domProps: {
						innerHTML: t._s(t.ofdDiv)
					},
					on: {
						mousewheel: t.scrool
					}
				})]), a("el-dialog", {
					attrs: {
						visible: t.dialogSignVisible,
						"close-on-click-modal": !1
					},
					on: {
						"update:visible": function(e) {
							t.dialogSignVisible = e
						}
					}
				}, [a("div", {
					staticClass: "SealContainer-content"
				}, [a("p", {
					staticClass: "content-title"
				}, [t._v("签章信息")]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("签章人")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSigner"
					}
				}, [t._v(t._s(t.spSigner))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("签章提供者")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spProvider"
					}
				}, [t._v(t._s(t.spProvider))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("原文摘要值")]), a("span", {
					staticClass: "value",
					staticStyle: {
						cursor: "pointer"
					},
					attrs: {
						id: "spHashedValue"
					},
					on: {
						click: function(e) {
							return t.showMore("原文摘要值", "spHashedValue")
						}
					}
				}, [t._v(t._s(t.spHashedValue))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("签名值")]), a("span", {
					staticClass: "value",
					staticStyle: {
						cursor: "pointer"
					},
					attrs: {
						id: "spSignedValue"
					},
					on: {
						click: function(e) {
							return t.showMore("签名值", "spSignedValue")
						}
					}
				}, [t._v(t._s(t.spSignedValue))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("签名算法")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSignMethod"
					}
				}, [t._v(t._s(t.spSignMethod))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("版本号")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spVersion"
					}
				}, [t._v(t._s(t.spVersion))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("验签结果")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "VerifyRet"
					}
				}, [t._v(t._s(t.VerifyRet))])]), a("p", {
					staticClass: "content-title"
				}, [t._v("印章信息")]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("印章标识")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSealID"
					}
				}, [t._v(t._s(t.spSealID))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("印章名称")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSealName"
					}
				}, [t._v(t._s(t.spSealName))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("印章类型")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSealType"
					}
				}, [t._v(t._s(t.spSealType))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("有效时间")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSealAuthTime"
					}
				}, [t._v(t._s(t.spSealAuthTime))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("制章日期")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSealMakeTime"
					}
				}, [t._v(t._s(t.spSealMakeTime))])]), a("div", {
					staticClass: "subcontent"
				}, [a("span", {
					staticClass: "title"
				}, [t._v("印章版本")]), a("span", {
					staticClass: "value",
					attrs: {
						id: "spSealVersion"
					}
				}, [t._v(t._s(t.spSealVersion))])])])]), a("el-dialog", {
					attrs: {
						title: t.title,
						visible: t.dialogFormVisible
					},
					on: {
						"update:visible": function(e) {
							t.dialogFormVisible = e
						}
					}
				}, [a("span", {
					staticStyle: {
						"text-align": "left"
					}
				}, [t._v(" " + t._s(t.value) + " ")]), a("div", {
					staticClass: "dialog-footer",
					attrs: {
						slot: "footer"
					},
					slot: "footer"
				}, [a("el-button", {
					attrs: {
						type: "primary"
					},
					on: {
						click: function(e) {
							t.dialogFormVisible = !1
						}
					}
				}, [t._v("确 定")])], 1)])], 1)
			},
			r = [],
			l = a("b85c"),
			d = (a("ac1f"), a("841c"), a("1276"), a("4de4"), a("d3b7"), a("e9c4"), a("5319"), a("b0c0"), a("99af"), a("c19f"), a("b64b"), a("b680"), a("2af1"), a("25f0"), a("a15b"), a("466d"), a("fb6a"), function(t, e) {
				return t * e / 25.4
			}),
			u = 10,
			f = u,
			p = function(t) {
				u = t > 5 ? 5 : t
			},
			h = function(t) {
				f = t > 1 ? t : 1, f = f > u ? u : f
			},
			v = function() {
				return f
			},
			g = function(t) {
				return d(t, 25.4 * f)
			},
			m = function() {
				return 1 / f
			},
			y = function(t, e, a) {
				var n = t * a.a + e * a.c + 1 * a.e,
					i = t * a.b + e * a.d + 1 * a.f;
				return {
					cx: n,
					cy: i
				}
			},
			b = function(t, e, a) {
				var n = t * a.a + e * a.c,
					i = t * a.b + e * a.d;
				return {
					ctmX: n,
					ctmY: i
				}
			},
			w = function(t, e, a) {
				var n = a.x + t,
					i = a.y + e;
				return {
					cx: n,
					cy: i
				}
			},
			C = function(t, e, a, n, i, o) {
				var s = 0,
					c = 0,
					r = 0,
					d = 0,
					u = [];
				if (!t) return u;
				var f, p = Object(l["a"])(t);
				try {
					for (p.s(); !(f = p.n())
						.done;) {
						var h = f.value;
						if (h) {
							if (s = parseFloat(h["X"]), c = parseFloat(h["Y"]), isNaN(s) && (s = 0), isNaN(c) && (c = 0), r = s, d = c, a) {
								var v = y(r, d, a);
								r = v.cx, d = v.cy
							}
							var m = [],
								C = [];
							h["DeltaX"] && h["DeltaX"].length > 0 && (m = h["DeltaX"]), h["DeltaY"] && h["DeltaY"].length > 0 && (C = h["DeltaY"]);
							var x = h["text"];
							if (x) {
								x += "", x = _(x), x = x.replace(/&#x20;/g, " ");
								for (var S = 0; S < x.length; S++) {
									if (S > 0 && m.length > 0)
										if (s += m[S - 1], a) {
											var A = b(m[S - 1], 0, a);
											r += A.ctmX
										} else r = s;
									if (S > 0 && C.length > 0)
										if (c += C[S - 1], a) {
											var M = b(0, C[S - 1], a);
											d += M.ctmY
										} else d = c;
									var I = w(r, d, n);
									o && (I = y(I.cx, I.cy, o));
									var O = x.substring(S, S + 1),
										F = {
											x: g(s),
											y: g(c),
											text: O,
											cx: g(I.cx),
											cy: g(I.cy)
										};
									u.push(F)
								}
							}
						}
					}
				} catch (E) {
					p.e(E)
				} finally {
					p.f()
				}
				if (u.length > 0) {
					var P, D = Object(l["a"])(e);
					try {
						for (D.s(); !(P = D.n())
							.done;)
							for (var T = P.value, k = T["CodePosition"], j = T["GlyphCount"], B = k; B < j + k; B++)
								if (u.length <= B) {
									var V = "".concat(u[u.length - 1].glyph, " ")
										.concat(T["Glyphs"][B - k]);
									u[u.length - 1].glyph = V
								} else u[B].glyph = T["Glyphs"][B - k]
					} catch (E) {
						D.e(E)
					} finally {
						D.f()
					}
				}
				return u
			},
			x = /&\w+;|&#(\d+);/g,
			S = {
				"&lt;": "<",
				"&gt;": ">",
				"&amp;": "&",
				"&nbsp;": " ",
				"&quot;": '"',
				"&copy;": "",
				"&apos;": "'"
			},
			_ = function(t) {
				return t = void 0 != t ? t : this.toString(), "string" != typeof t ? t : t.replace(x, (function(t, e) {
					var a = S[t];
					return void 0 == a && (a = isNaN(e) ? t : String.fromCharCode(160 == e ? 32 : e)), a
				}))
			},
			A = {
				"楷体": "楷体, KaiTi, Kai, simkai",
				kaiti: "楷体, KaiTi, Kai, simkai",
				Kai: "楷体, KaiTi, Kai",
				simsun: "SimSun, simsun, Songti SC",
				"宋体": "SimSun, simsun, Songti SC",
				"黑体": "SimHei, STHeiti, simhei",
				"仿宋": "FangSong, STFangsong, simfang",
				"小标宋体": "sSun",
				"方正小标宋_gbk": "sSun",
				"仿宋_gb2312": "FangSong, STFangsong, simfang",
				"楷体_gb2312": "楷体, KaiTi, Kai, simkai",
				couriernew: "Courier New",
				"courier new": "Courier New"
			},
			M = function(t) {
				if (!t) return A["宋体"];
				var e = t.FamilyName;
				e || (e = t.FontName), A[e.toLowerCase()] && (e = A[e.toLowerCase()]);
				for (var a = 0, n = Object.keys(A); a < n.length; a++) {
					var i = n[a];
					if (-1 != e.toLowerCase()
						.indexOf(i.toLowerCase())) return A[i]
				}
				return e
			},
			I = function(t) {
				if (t) {
					var e = t.split(" ");
					return {
						x: parseFloat(e[0]),
						y: parseFloat(e[1]),
						w: parseFloat(e[2]),
						h: parseFloat(e[3])
					}
				}
				return null
			},
			O = function(t) {
				var e = t.split(" ");
				return e
			},
			F = function(t) {
				if (t && t.length > 0) {
					if (-1 !== t.indexOf("#")) return t = t.replace(/#/g, ""), t = t.replace(/ /g, ""), t = "#" + t.toString(), t;
					var e = t.split(" ");
					return "rgb(".concat(e[0], ", ")
						.concat(e[1], ", ")
						.concat(e[2], ")")
				}
				return "rgb(0, 0, 0)"
			},
			P = function(t) {
				return {
					x: g(t.x),
					y: g(t.y),
					w: g(t.w),
					h: g(t.h)
				}
			},
			D = function(t) {
				return t && 0 === t.indexOf("/") && (t = t.replace("/", "")), t
			},
			T = function(t, e) {
				return t && 0 === t.indexOf(e) && (t = t.replace(e, "")), t
			},
			k = function(t) {
				if ("string" === typeof t) return t;
				for (var e = "", a = t, n = 0; n < a.length; n++) {
					var i = a[n].toString(2),
						o = i.match(/^1+?(?=0)/);
					if (o && 8 == i.length) {
						for (var s = o[0].length, c = a[n].toString(2)
							.slice(7 - s), r = 1; r < s; r++) c += a[r + n].toString(2)
							.slice(2);
						e = "".concat(e)
							.concat(String.fromCharCode(parseInt(c, 2))), n += s - 1
					}
				}
				return e
			},
			j = (a("5cc6"), a("907a"), a("9a8c"), a("a975"), a("735e"), a("c1ac"), a("d139"), a("3a7b"), a("d5d6"), a("82f8"), a("e91f"), a("60bd"), a("5f96"), a("3280"), a("3fcc"), a("ca91"), a("25a1"), a("cd26"), a("3c5d"), a("2954"), a("649e"), a("219c"), a("170b"), a("b39a"), a("72f7"), a("143c"), window["FreetypeKit"]),
			B = function(t) {
				j.wasmModule["_ASC_FT_Free"](t.data)
			},
			V = function() {
				n = j.wasmModule["_ASC_FT_Init"]()
			},
			E = function(t) {
				var e = new Uint8Array(t),
					a = j.wasmModule["_malloc"](t.byteLength),
					n = j.wasmModule.HEAPU8;
				return n.set(e, a), {
					data: a,
					length: t.byteLength
				}
			},
			R = function(t) {
				var e = j.wasmModule["_ASC_FT_GetFaceInfo"](t);
				if (e) {
					var a = new j.CFaceInfo,
						n = Math.min(j.wasmModule["HEAP8"].length - e >> 2, 250),
						i = new Int32Array(j.wasmModule["HEAP8"].buffer, e, n),
						o = 0;
					a.units_per_EM = Math.abs(i[o++]), a.ascender = i[o++], a.descender = i[o++], a.height = i[o++], a.face_flags = i[o++], a.num_faces = i[o++], a.num_glyphs = i[o++], a.num_charmaps = i[o++], a.style_flags = i[o++], a.face_index = i[o++];
					var s = i[o++];
					while (s) a.family_name += String.fromCharCode(s), s = i[o++];
					s = i[o++];
					while (s) a.style_name += String.fromCharCode(s), s = i[o++];
					a.os2_version = i[o++], a.os2_usWeightClass = i[o++], a.os2_fsSelection = i[o++], a.os2_usWinAscent = i[o++], a.os2_usWinDescent = i[o++], a.os2_usDefaultChar = i[o++], a.os2_sTypoAscender = i[o++], a.os2_sTypoDescender = i[o++], a.os2_sTypoLineGap = i[o++], a.os2_ulUnicodeRange1 = $(i[o++]), a.os2_ulUnicodeRange2 = $(i[o++]), a.os2_ulUnicodeRange3 = $(i[o++]), a.os2_ulUnicodeRange4 = $(i[o++]), a.os2_ulCodePageRange1 = $(i[o++]), a.os2_ulCodePageRange2 = $(i[o++]), a.os2_nSymbolic = i[o++], a.header_yMin = i[o++], a.header_yMax = i[o++];
					for (var c = i[o++], r = 0; r < c; r++) a.monochromeSizes.push(i[o++]);
					return j.wasmModule["_ASC_FT_Free"](e), e = null, a
				}
			},
			N = function(t, e) {
				var a = j.wasmModule["_AST_FT_Load_Glyph"](t, e, 1);
				if (a > 0) return " ";
				var n = j.wasmModule["_ASC_FT_Get_Glyph_Measure_Params"](t, 1);
				if (!n) return null;
				var i = j.wasmModule["HEAP32"][n >> 2],
					o = new Int32Array(j.wasmModule["HEAP8"].buffer, n, i),
					s = new j.CGlyphMetrics;
				s.bbox_xMin = o[1], s.bbox_yMin = o[2], s.bbox_xMax = o[3], s.bbox_yMax = o[4], s.width = o[5], s.height = o[6], s.horiAdvance = o[7], s.horiBearingX = o[8], s.horiBearingY = o[9], s.vertAdvance = o[10], s.vertBearingX = o[11], s.vertBearingY = o[12], s.linearHoriAdvance = o[13], s.linearVertAdvance = o[14], s.horiUnderlinePosition = s.horiBearingY - s.height;
				var c = 15,
					r = "";
				while (c < i) switch (o[c++]) {
					case 0:
						r += " M ".concat(o[c++], " ")
							.concat(o[c++]);
						break;
					case 1:
						r += " L ".concat(o[c++], " ")
							.concat(o[c++]);
						break;
					case 2:
						r += " Q ".concat(o[c++], " ")
							.concat(o[c++], " ")
							.concat(o[c++], " ")
							.concat(o[c++]);
						break;
					case 3:
						r += " C ".concat(o[c++], " ")
							.concat(o[c++], " ")
							.concat(o[c++], " ")
							.concat(o[c++], " ")
							.concat(o[c++], " ")
							.concat(o[c++]);
						break;
					default:
						break
				}
				return s.glyphPath = r, j.wasmModule["_ASC_FT_Free"](n), o = null, s
			},
			L = function(t) {
				var e = E(t),
					a = j.wasmModule["JSReadOFD"](n, e.data, e.length);
				return B(e), a
			},
			H = function(t, e, a) {
				var n = E(t),
					i = j.wasmModule["JSBase64ByHashFile"](n.data, n.length, e, a);
				return i
			},
			W = function(t) {
				var e = j.wasmModule["JSGetImageInfoByBase64"](t);
				return "jb2" != e.ext && "jp2" != e.ext && "tif" != e.ext || (e.ext = "png"), 0 === e.fileBase64.length && (e.fileBase64 = t), 0 !== e.fileBase64.indexOf("data:image") && (e.fileBase64 = "data:image/".concat(e.ext, ";base64,")
					.concat(e.fileBase64)), e
			},
			$ = (j.CFaceInfo, function(t) {
				return t < 0 ? t + 4294967296 : t
			}),
			G = function(t, e, a) {
				var n, i = a["Page"]["Area"];
				if (i) {
					var o = i["PhysicalBox"];
					if (o) n = o;
					else {
						var s = i["ApplicationBox"];
						if (s) n = s;
						else {
							var c = i["ContentBox"];
							c && (n = c)
						}
					}
				} else {
					var r = e["CommonData"]["PageArea"],
						l = r["PhysicalBox"];
					if (l) n = l;
					else {
						var d = r["ApplicationBox"];
						if (d) n = d;
						else {
							var u = r["ContentBox"];
							u && (n = u)
						}
					}
				}
				var f = ((t - 10) / parseFloat(n.w))
					.toFixed(1);
				return p(f), h(f), n = P(n), n
			},
			U = function(t, e) {
				var a, n = Object(l["a"])(t);
				try {
					for (n.s(); !(a = n.n())
						.done;) {
						var i = a.value;
						if (i.ID == e) return i
					}
				} catch (o) {
					n.e(o)
				} finally {
					n.f()
				}
			},
			q = function(t, e) {
				var a, n = Object(l["a"])(t);
				try {
					for (n.s(); !(a = n.n())
						.done;) {
						var i = a.value;
						if (i.ID == e) return i
					}
				} catch (o) {
					n.e(o)
				} finally {
					n.f()
				}
			},
			X = function(t, e) {
				var a, n = Object(l["a"])(t);
				try {
					for (n.s(); !(a = n.n())
						.done;) {
						var i = a.value;
						if (i.ID == e) return i
					}
				} catch (o) {
					n.e(o)
				} finally {
					n.f()
				}
			},
			Y = function(t, e, a) {
				var n = e.Page.Template,
					i = a["Annotations"],
					o = a["CommonData"]["TemplatePage"],
					s = a["Signatures"]["Signature"],
					c = a["CommonData"]["Res"]["Fonts"],
					r = a["CommonData"]["Res"]["DrawParams"],
					d = (a["CommonData"]["Res"]["ColorSpaces"], a["CommonData"]["Res"]["CompositeGraphicUnits"]),
					u = a["CommonData"]["Res"]["MultiMedias"];
				if (n) {
					var f, p = Object(l["a"])(n);
					try {
						for (p.s(); !(f = p.n())
							.done;) {
							var h, v = f.value,
								g = Object(l["a"])(o);
							try {
								for (g.s(); !(h = g.n())
									.done;) {
									var m = h.value;
									if (m.ID == v["TemplateID"]) {
										if (m["Page"]["Content"]) {
											var y, b = m["Page"]["Content"]["Layer"],
												w = Object(l["a"])(b);
											try {
												for (w.s(); !(y = w.n())
													.done;) {
													var C = y.value;
													J(t, c, r, u, d, C, !1)
												}
											} catch (X) {
												w.e(X)
											} finally {
												w.f()
											}
										}
										break
									}
								}
							} catch (X) {
								g.e(X)
							} finally {
								g.f()
							}
						}
					} catch (X) {
						p.e(X)
					} finally {
						p.f()
					}
				}
				if (e["Page"]["Content"]) {
					var x, S = e["Page"]["Content"]["Layer"],
						_ = Object(l["a"])(S);
					try {
						for (_.s(); !(x = _.n())
							.done;) {
							var A = x.value;
							J(t, c, r, u, d, A, !1)
						}
					} catch (X) {
						_.e(X)
					} finally {
						_.f()
					}
				}
				var M, I = Object(l["a"])(s);
				try {
					for (I.s(); !(M = I.n())
						.done;) {
						var O, F = M.value,
							D = Object(l["a"])(F["Signature"]["SignedInfo"]["StampAnnot"]);
						try {
							for (D.s(); !(O = D.n())
								.done;) {
								var T = O.value,
									k = T["PageRef"];
								if (e.ID == k) {
									var j = T["Boundary"];
									if ("ofd" === F.Signature.PicType)
										for (var B = F.Signature.ofd.Document[0], V = 0; V < B.Pages.length; V++) {
											var E = B.Pages[V];
											z(t, E, B, !0, j, k, T.ID)
										} else {
											var R = P(j),
												N = "data:image/".concat(F.Signature.picType, ";base64,")
												.concat(F.Signature.PicValue),
												L = Z(t.style.width, t.style.height, N, R.w, R.h, R, T.Clip, !0, k, T.ID);
											t.appendChild(L)
										}
									break
								}
							}
						} catch (X) {
							D.e(X)
						} finally {
							D.f()
						}
					}
				} catch (X) {
					I.e(X)
				} finally {
					I.f()
				}
				var H, W = Object(l["a"])(i);
				try {
					for (W.s(); !(H = W.n())
						.done;) {
						var $ = H.value;
						if (e.ID == $.PageID) {
							var G, U = Object(l["a"])($.PageAnnot.Annot);
							try {
								for (U.s(); !(G = U.n())
									.done;) {
									var q = G.value;
									K(t, q, c, r, u, d)
								}
							} catch (X) {
								U.e(X)
							} finally {
								U.f()
							}
							break
						}
					}
				} catch (X) {
					W.e(X)
				} finally {
					W.f()
				}
			},
			K = function(t, e, a, n, i, o) {
				var s = document.createElement("div");
				s.setAttribute("style", "overflow: hidden;;position:relative;");
				var c = e["Appearance"]["Boundary"];
				if (c) {
					var r = P(c);
					s.setAttribute("style", "overflow: hidden;position:absolute; left: ".concat(r.x, "px; top: ")
						.concat(r.y, "px; width: ")
						.concat(r.w, "px; height: ")
						.concat(r.h, "px"))
				} else s.setAttribute("style", "overflow: visible;position:absolute; left: 0px; top: 0px; width: 1px; height: 1px");
				var l = e["Appearance"];
				J(s, a, n, i, o, l, !1), t.appendChild(s)
			},
			z = function(t, e, a, n, i, o, s) {
				var c = {
					x: 0,
					y: 0,
					w: 0,
					h: 0
				};
				n && (c = i);
				var r = P(c),
					l = document.createElement("div");
				l.setAttribute("name", "seal_img_div"), l.setAttribute("style", "cursor: pointer; position:relative; left: ".concat(r.x, "px; top: ")
					.concat(r.y, "px; width: ")
					.concat(r.w, "px; height: ")
					.concat(r.h, "px;overflow:hidden")), l.setAttribute("data-signature-id", s), l.setAttribute("data-page-ref", o), Y(l, e, a), t.appendChild(l)
			},
			J = function t(e, a, n, i, o, s, c, r, d, u) {
				var f = null,
					p = null,
					h = g(.353),
					v = s["DrawParam"];
				if (v && X(n, v)) {
					var m = X(n, v);
					X(n, m["Relative"]) && (m = X(n, m["Relative"])), m["FillColor"] && (f = F(m["FillColor"]["Value"])), m["StrokeColor"] && (f = F(m["StrokeColor"]["Value"])), m["LineWidth"] && (h = g(m["LineWidth"]))
				}
				var y, b = s["PageBlock"],
					w = Object(l["a"])(b);
				try {
					for (w.s(); !(y = w.n())
						.done;) {
						var C = y.value;
						if ("ImageObject" == C["Type"]) {
							var x = Q(e.style.width, e.style.height, i, C, c, d, u);
							e.appendChild(x)
						} else if ("PathObject" == C["Type"]) {
							var S = et(n, C, f, p, h, c, r, d, u);
							e.appendChild(S)
						} else if ("TextObject" == C["Type"]) {
							var _ = tt(e, a, C, f, p, n, r, d, u);
							_.childElementCount > 0 && e.appendChild(_)
						} else if ("CompositeObject" == C["Type"]) {
							var A, M = Object(l["a"])(o);
							try {
								for (M.s(); !(A = M.n())
									.done;) {
									var I = A.value;
									if (I["ID"] === C["ResourceID"]) {
										var O = C["Alpha"],
											P = C["Boundary"],
											D = C["CTM"];
										t(e, a, n, i, o, I, !1, O, P, D);
										break
									}
								}
							} catch (T) {
								M.e(T)
							} finally {
								M.f()
							}
						}
					}
				} catch (T) {
					w.e(T)
				} finally {
					w.f()
				}
			},
			Q = function(t, e, a, n, i, o, s) {
				var c = n["Boundary"];
				c = P(c);
				var r = n["ResourceID"],
					l = q(a, r);
				if (!(l.width > 0 || l.height > 0)) {
					var d = W(l.FileBase64);
					l.width = d.width, l.height = d.height, l.FileBase64 = d.fileBase64
				}
				var u = n["CTM"];
				return Z(t, e, l.FileBase64, l.width, l.height, c, !1, i, null, null, u, o, s)
			},
			Z = function(t, e, a, n, i, o, s, c, r, l, d, u, f) {
				var p = parseFloat(t.replace("px", "")),
					h = parseFloat(e.replace("px", "")),
					v = o.w > p ? p : o.w,
					y = o.h > h ? h : o.h,
					b = document.createElementNS("http://www.w3.org/2000/svg", "svg"),
					w = document.createElementNS("http://www.w3.org/2000/svg", "image");
				c && (b.setAttribute("viewbox", "0 0 ".concat(v, " ")
					.concat(y)), b.setAttribute("name", "seal_img_div"), b.setAttribute("data-signature-id", l), b.setAttribute("data-page-ref", r), w.setAttribute("preserveAspectRatio", "none slice"));
				var C = n,
					x = i;
				w.setAttribute("xlink:href", a), w.href.baseVal = a, w.setAttribute("width", "".concat(C, "px")), w.setAttribute("height", "".concat(x, "px"));
				var S = "";
				d && (S = "matrix(".concat(d.a / C / m(), " ")
					.concat(d.b / C / m(), " ")
					.concat(d.c / x / m(), " ")
					.concat(d.d / x / m(), " ")
					.concat(g(d.e), " ")
					.concat(g(d.f), ")")), f && (S = "".concat(S, " matrix(")
					.concat(f.a, " ")
					.concat(f.b, " ")
					.concat(f.c, " ")
					.concat(f.d, " ")
					.concat(g(f.e), " ")
					.concat(g(f.f), ")")), w.setAttribute("transform", S), b.appendChild(w);
				var _ = "";
				s && (s = P(s), _ = "clip: rect(".concat(s.y, "px, ")
					.concat(s.w + s.x, "px, ")
					.concat(s.h + s.y, "px, ")
					.concat(s.x, "px)"));
				var A = o.x,
					M = o.y;
				if (f) {
					var I = f.a,
						O = f.b,
						F = f.c,
						D = f.d,
						T = I > 0 ? Math.sign(I) * Math.sqrt(I * I + F * F) : Math.sqrt(I * I + F * F),
						k = D > 0 ? Math.sign(D) * Math.sqrt(O * O + D * D) : Math.sqrt(O * O + D * D),
						j = Math.atan2(-O, D);
					0 == j && 0 != I && 1 == D || (M *= k, A *= T)
				}
				return b.setAttribute("style", "cursor: pointer; overflow: visible; position: absolute; left: ".concat(A, "px; top: ")
					.concat(M, "px; width: ")
					.concat(v, "px; height: ")
					.concat(y, "px; ")
					.concat(_, ";")), b
			},
			tt = function(t, e, a, n, i, o, s, c, r) {
				var d = 1,
					u = a["Boundary"];
				u = P(u);
				var f = a["CTM"],
					p = a["HScale"],
					h = a["Font"],
					v = a["Weight"],
					m = g(parseFloat(a["Size"])),
					y = C(a["TextCode"], a["CGTransform"], f, a["Boundary"], c, r),
					b = document.createElementNS("http://www.w3.org/2000/svg", "svg");
				b.setAttribute("version", "1.1");
				var w = a["FillColor"],
					x = !1,
					S = a["DrawParam"];
				if (S) {
					var _ = X(o, S);
					_["FillColor"] && (n = F(_["FillColor"]))
				}
				if (w) {
					w["Value"] && (n = F(w["Value"]));
					var A = w["Alpha"];
					A && (d = A > 1 ? A / 255 : A);
					var I = w["AxialShd"];
					if (I) {
						x = !0;
						var O = document.createElement("linearGradient");
						O.setAttribute("id", "".concat(a["ID"])), O.setAttribute("x1", "0%"), O.setAttribute("y1", "0%"), O.setAttribute("x2", "100%"), O.setAttribute("y2", "100%");
						var D, T = Object(l["a"])(I["ofd:Segment"]);
						try {
							for (T.s(); !(D = T.n())
								.done;) {
								var k = D.value;
								if (k) {
									var j = document.createElement("stop");
									j.setAttribute("offset", "".concat(100 * k["@_Position"], "%")), j.setAttribute("style", "stop-color:".concat(F(k["ofd:Color"]["@_Value"]), ";stop-opacity:1")), O.appendChild(j), n = F(k["ofd:Color"]["@_Value"])
								}
							}
						} catch (vt) {
							T.e(vt)
						} finally {
							T.f()
						}
						b.appendChild(O)
					}
				}
				a["Fill"] || (d = 0);
				var B = u.w,
					V = u.h,
					E = u.x,
					L = u.y,
					H = m,
					W = m;
				if (f) {
					var $ = f.a,
						G = f.b,
						q = f.c,
						Y = f.d,
						K = $ > 0 ? Math.sign($) * Math.sqrt($ * $ + q * q) : Math.sqrt($ * $ + q * q),
						z = Y > 0 ? Math.sign(Y) * Math.sqrt(G * G + Y * Y) : Math.sqrt(G * G + Y * Y),
						J = Math.atan2(-G, Y);
					0 == J && 0 != $ && 1 == Y || (H *= K, W *= z), 0 == J && (p = $ / Y, p > 0 && (H *= p))
				}
				if (r) {
					var Q = r.a,
						Z = r.b,
						tt = r.c,
						et = r.d,
						nt = Q > 0 ? Math.sign(Q) * Math.sqrt(Q * Q + tt * tt) : Math.sqrt(Q * Q + tt * tt),
						it = et > 0 ? Math.sign(et) * Math.sqrt(Z * Z + et * et) : Math.sqrt(Z * Z + et * et),
						ot = Math.atan2(-Z, et);
					0 == ot && 0 != Q && 1 == et || (H *= nt, W *= it, L *= it, E *= nt, B *= nt, V *= it)
				}
				var st, ct = U(e, h),
					rt = Object(l["a"])(y);
				try {
					for (rt.s(); !(st = rt.n())
						.done;) {
						var lt = st.value;
						if (lt && !isNaN(lt.x))
							if (lt.glyph) {
								if (ct && ct["face"]) {
									var dt = R(ct["face"]),
										ut = N(ct["face"], lt.glyph),
										ft = at(ut.glyphPath, ut.horiUnderlinePosition, dt.units_per_EM, H, W, i || n, d);
									ft.img.setAttribute("style", "position:absolute; left: ".concat(Math.ceil(lt.cx), "px; top: ")
										.concat(lt.cy - ft.yScale * ut.horiUnderlinePosition - W, "px;width: ")
										.concat(Math.ceil(H), "px; height:")
										.concat(Math.ceil(W), "px")), t.appendChild(ft.img)
								}
							} else {
								var pt = document.createElementNS("http://www.w3.org/2000/svg", "text");
								pt.setAttribute("x", lt.x), pt.setAttribute("y", lt.y), pt.innerHTML = lt.text;
								var ht = "";
								f && (ht = "matrix(".concat(f.a, " ")
									.concat(f.b, " ")
									.concat(f.c, " ")
									.concat(f.d, " ")
									.concat(g(f.e), " ")
									.concat(g(f.f), ")")), r && (ht = "".concat(ht, " matrix(")
									.concat(r.a, " ")
									.concat(r.b, " ")
									.concat(r.c, " ")
									.concat(r.d, " ")
									.concat(g(r.e), " ")
									.concat(g(r.f), ")")), p && (ht = "".concat(ht, " matrix(")
									.concat(p, ", 0, 0, 1, ")
									.concat((1 - p) * lt.x, ", 0)")), pt.setAttribute("transform", ht), x ? pt.setAttribute("fill", n) : (pt.setAttribute("fill", i), pt.setAttribute("fill", n), pt.setAttribute("fill-opacity", d)), pt.setAttribute("style", "font-weight: ".concat(v, ";font-size:")
									.concat(m, "px;font-family: ")
									.concat(M(ct), ";")), b.appendChild(pt)
							}
					}
				} catch (vt) {
					rt.e(vt)
				} finally {
					rt.f()
				}
				return b.setAttribute("style", "overflow:hidden;position:absolute;width:".concat(B, "px;height:")
					.concat(V, "px;left:")
					.concat(E, "px;top:")
					.concat(L, "px;")), b
			},
			et = function(t, e, a, n, i, o, s, c, r) {
				var d = document.createElementNS("http://www.w3.org/2000/svg", "svg");
				d.setAttribute("version", "1.1");
				var u = e["Boundary"];
				if (!u) return d;
				u = P(u);
				var f = e["LineWidth"],
					p = e["AbbreviatedData"],
					h = e["CTM"],
					v = document.createElementNS("http://www.w3.org/2000/svg", "path");
				f && (i = g(f));
				var m = e["DrawParam"];
				if (m) {
					var y = X(t, m);
					f = y.LineWidth, f && (i = g(f))
				}
				h && v.setAttribute("transform", "matrix(".concat(h.a, " ")
					.concat(h.b, " ")
					.concat(h.c, " ")
					.concat(h.d, " ")
					.concat(g(h.e), " ")
					.concat(g(h.f), ")"));
				var b = e["StrokeColor"];
				if (b) {
					b["Value"] && (n = F(b["Value"]));
					var w = b["AxialShd"];
					if (w) {
						!0;
						var C = document.createElement("linearGradient");
						C.setAttribute("id", "".concat(e["ID"])), C.setAttribute("x1", "0%"), C.setAttribute("y1", "0%"), C.setAttribute("x2", "100%"), C.setAttribute("y2", "100%");
						var x, S = Object(l["a"])(w["ofd:Segment"]);
						try {
							for (S.s(); !(x = S.n())
								.done;) {
								var _ = x.value;
								if (_) {
									var A = document.createElement("stop");
									A.setAttribute("offset", "".concat(100 * _["Position"], "%")), A.setAttribute("style", "stop-color:".concat(F(_["Color"]["Value"]), ";stop-opacity:1")), C.appendChild(A), n = F(_["ofd:Color"]["@_Value"])
								}
							}
						} catch (at) {
							S.e(at)
						} finally {
							S.f()
						}
						d.appendChild(C)
					}
				}
				var M = e["FillColor"];
				if (M) {
					M["Value"] && (a = F(M["Value"])), M["Alpha"] && 0 == M["Alpha"] && (a = "none");
					var D = M["AxialShd"];
					if (D) {
						!0;
						var T = document.createElement("linearGradient");
						T.setAttribute("id", "".concat(e["@_ID"])), T.setAttribute("x1", "0%"), T.setAttribute("y1", "0%"), T.setAttribute("x2", "100%"), T.setAttribute("y2", "100%");
						var k, j = Object(l["a"])(D["ofd:Segment"]);
						try {
							for (j.s(); !(k = j.n())
								.done;) {
								var B = k.value;
								if (B) {
									var V = document.createElement("stop");
									V.setAttribute("offset", "".concat(100 * B["Position"], "%")), V.setAttribute("style", "stop-color:".concat(F(B["Color"]["Value"]), ";stop-opacity:1")), T.appendChild(V), a = F(B["ofd:Color"]["@_Value"])
								}
							}
						} catch (at) {
							j.e(at)
						} finally {
							j.f()
						}
						d.appendChild(T)
					}
				}
				if (i > 0 && !n && (n = a, n || (n = "rgb(0, 0, 0)")), s && v.setAttribute("fill-opacity", "".concat(s / 255)), e["Stroke"] && (v.setAttribute("stroke", "".concat(n)), v.setAttribute("stroke-width", "".concat(i, "px"))), e["Fill"] ? v.setAttribute("fill", "".concat(o ? "none" : a || "none")) : v.setAttribute("fill", "none"), e["Join"] && v.setAttribute("stroke-linejoin", "".concat(e["@_Join"])), e["Cap"] && v.setAttribute("stroke-linecap", "".concat(e["@_Cap"])), e["DashPattern"]) {
					var E = e["DashPattern"],
						R = O(E),
						N = 0;
					e["DashOffset"] && (N = e["DashOffset"]), v.setAttribute("stroke-dasharray", "".concat(g(R[0]), ",")
						.concat(g(R[1]))), v.setAttribute("stroke-dashoffset", "".concat(g(N), "px"))
				}
				var L, H = "",
					W = Object(l["a"])(p);
				try {
					for (W.s(); !(L = W.n())
						.done;) {
						var $ = L.value;
						"M" === $.cmd || "S" === $.cmd ? H += "M ".concat(g($.crds[0]), " ")
							.concat(g($.crds[1]), " ") : "L" === $.cmd ? H += "L ".concat(g($.crds[0]), " ")
							.concat(g($.crds[1]), " ") : "B" === $.cmd ? H += "C ".concat(g($.crds[0]), " ")
							.concat(g($.crds[1]), " ")
							.concat(g($.crds[2]), " ")
							.concat(g($.crds[3]), " ")
							.concat(g($.crds[4]), " ")
							.concat(g($.crds[5]), " ") : "Q" === $.cmd ? H += "Q ".concat(g($.crds[0]), " ")
							.concat(g($.crds[1]), " ")
							.concat(g($.crds[2]), " ")
							.concat(g($.crds[3]), " ") : "A" === $.cmd ? H += "A ".concat(g($.crds[0]), ",")
							.concat(g($.crds[1]), " ")
							.concat(g($.crds[2]), " ")
							.concat(g($.crds[3]), ",")
							.concat(g($.crds[4]), " ")
							.concat(g($.crds[5]), ",")
							.concat(g($.crds[6])) : "C" === $.cmd && (H += "Z")
					}
				} catch (at) {
					W.e(at)
				} finally {
					W.f()
				}
				v.setAttribute("d", H), d.appendChild(v);
				var G = o ? u.w : Math.ceil(u.w),
					U = o ? u.h : Math.ceil(u.h),
					q = u.x,
					Y = u.y;
				if (d.setAttribute("style", "overflow:hidden;position:absolute;width:".concat(G, "px;height:")
					.concat(U, "px;left:")
					.concat(q, "px;top:")
					.concat(Y, "px;")), c) {
					var K = document.createElementNS("http://www.w3.org/2000/svg", "svg");
					K.setAttribute("version", "1.1");
					var z = I(c);
					z = P(z);
					var J = Math.ceil(z.w),
						Q = Math.ceil(z.h),
						Z = z.x,
						tt = z.y;
					if (K.setAttribute("style", "overflow:hidden;position:absolute;width:".concat(J, "px;height:")
						.concat(Q, "px;left:")
						.concat(Z, "px;top:")
						.concat(tt, "px;")), r) {
						var et = O(r);
						d.setAttribute("transform", "matrix(".concat(et[0], " ")
							.concat(et[1], " ")
							.concat(et[2], " ")
							.concat(et[3], " ")
							.concat(g(et[4]), " ")
							.concat(g(et[5]), ")"))
					}
					return K.appendChild(d), K
				}
				return d
			},
			at = function(t, e, a, n, i, o, s) {
				var c = n / a,
					r = i / a,
					l = document.createElementNS("http://www.w3.org/2000/svg", "svg");
				l.setAttribute("version", "1.1");
				var d = document.createElementNS("http://www.w3.org/2000/svg", "path");
				return d.setAttribute("transform", "translate(0, ".concat(i, ") scale(")
					.concat(c, ", ")
					.concat(-r, ") translate(0, ")
					.concat(-e, ")")), d.setAttribute("d", t), o && d.setAttribute("fill", o), d.setAttribute("fill-opacity", s), l.appendChild(d), {
					img: l,
					yScale: r
				}
			},
			nt = function(t) {
				if (t.ofd instanceof File) {
					var e = new FileReader;
					e.readAsArrayBuffer(t.ofd), e.onload = function() {
						t.success && t.success(H(e.result, t.list, t.method))
					}
				} else t.ofd instanceof ArrayBuffer && t.success && t.success(H(t.ofd, t.list, t.method))
			},
			it = function(t) {
				if (V(), t.ofd instanceof File) {
					var e = new FileReader;
					e.readAsArrayBuffer(t.ofd), e.onload = function() {
						t.success && t.success(L(e.result))
					}
				} else t.ofd instanceof ArrayBuffer && t.success && t.success(L(t.ofd))
			},
			ot = function(t, e) {
				var a = [];
				if (!e) return a;
				for (var n = 0; n < e.Pages.length; n++) {
					var i = e.Pages[n],
						o = G(t, e, i),
						s = document.createElement("div");
					s.setAttribute("style", "margin-bottom: 20px;position: relative;width:".concat(o.w, "px;height:")
						.concat(o.h, "px;background: white;overflow: hidden")), Y(s, i, e), a.push(s)
				}
				return a
			},
			st = function() {
				return v()
			},
			ct = {
				name: "HelloWorld",
				data: function() {
					return {
						dragseal: !1,
						docleft: 0,
						pdfFile: null,
						ofdFile: null,
						ofdBase64: null,
						loading: !1,
						pageIndex: 1,
						pageCount: 0,
						scale: 0,
						title: null,
						value: null,
						dialogFormVisible: !1,
						ofdObj: null,
						screenWidth: document.body.clientWidth,
						ofdDiv: null,
						isFont: !1,
						currentFile: null,
						dialogSignVisible: !1,
						VerifyRet: null,
						spSigner: null,
						spProvider: null,
						spHashedValue: null,
						spSignedValue: null,
						spSignMethod: null,
						spSealID: null,
						spSealName: null,
						spSealType: null,
						spSealAuthTime: null,
						spSealMakeTime: null,
						spSealVersion: null,
						spVersion: null
					}
				},
				created: function() {
					this.pdfFile = null, this.file = null
				},
				mounted: function() {
					var t = this.getQueryVariable("file");
					t = decodeURIComponent(t), t = decodeURIComponent(t), "" == t || null == t || void 0 == t ? window.alert("地址为空") : this.demo(t)
				},
				methods: {
					getQueryVariable: function(t) {
						for (var e = window.location.search.substring(1), a = e.split("&"), n = 0; n < a.length; n++) {
							var i = a[n].split("=");
							if (i[0] == t) return i[1]
						}
						eturn(!1)
					},
					print: function() {
						var t = this.$refs["contentDiv"],
							e = t.children;
						this.loading = !0, this.progressVisible = !0;
						var a, n = [],
							i = 0,
							o = Object(l["a"])(e);
						try {
							for (o.s(); !(a = o.n())
								.done;) {
								var s = a.value;
								n.push(s.cloneNode(!0)), this.percentage = i / e.length
							}
						} catch (p) {
							o.e(p)
						} finally {
							o.f()
						}
						if (n.length > 0) {
							var c = window.open("打印窗口", "_blank"),
								r = c.document.body;
							console.log(n.length);
							var d, u = Object(l["a"])(n);
							try {
								for (u.s(); !(d = u.n())
									.done;) {
									var f = d.value;
									r.appendChild(f)
								}
							} catch (p) {
								u.e(p)
							} finally {
								u.f()
							}
							this.progressVisible = !1, this.loading = !1, c.focus(), c.print(), c.close()
						}
					},
					printSecret: function() {
						var t, e = document.getElementsByTagName("img"),
							a = Object(l["a"])(e);
						try {
							for (a.s(); !(t = a.n())
								.done;) {
								var n = t.value;
								n.style.filter = "grayscale(100%)"
							}
						} catch (c) {
							a.e(c)
						} finally {
							a.f()
						}
						this.print();
						var i, o = Object(l["a"])(e);
						try {
							for (o.s(); !(i = o.n())
								.done;) {
								var s = i.value;
								s.style.filter = ""
							}
						} catch (c) {
							o.e(c)
						} finally {
							o.f()
						}
					},
					createSealImg: function() {
						var t = document.getElementById("content"),
							e = document.createElement("div");
						e.setAttribute("class", "seal"), e.setAttribute("id", "seal"), e.setAttribute("style", "display:none;top:0px;position: absolute;z-index: 999999;"), t.appendChild(e), e.innerHTML = "<img src='./seal.png' id='sealimage' style='opacity:0.9;' height='210px' width='210px'>"
					},
					zoomSeal: function() {
						this.dragseal = !0;
						var t = this,
							e = document.getElementById("seal"),
							a = document.getElementById("content"),
							n = document.getElementById("sealimage"),
							i = document.getElementById("main");
						e.style.display = "block", document.onmousemove = function(o) {
							e.style.top = i.scrollTop + o.clientY - a.offsetTop - n.width / 2 + "px", e.style.left = i.scrollLeft - t.leftMenu_width + o.clientX - n.height / 2 + "px"
						}, e.onmousedown = function() {
							document.onmousemove = function(o) {
								e.style.top = i.scrollTop + o.clientY - a.offsetTop - n.width / 2 + "px", e.style.left = i.scrollLeft - t.leftMenu_width + o.clientX - n.height / 2 + "px"
							}, document.onmouseup = function() {
								t.docleft = parseFloat(e.style.left) - t.leftMenu_width - a.childNodes[0].offsetLeft, document.onmousemove = null, document.onmouseup = null
							}
						}
					},
					getSealPosition: function() {
						var t = document.getElementById("seal"),
							e = document.getElementById("content"),
							a = document.getElementById("sealimage"),
							n = parseFloat(t.style.left),
							i = parseFloat(t.style.top),
							o = (n - e.childNodes[0].offsetLeft) / this.scale,
							s = parseFloat(e.childNodes[0].style.height),
							c = Math.ceil(i / s),
							r = (i % s - 20 * c) / this.scale,
							l = {
								x: o,
								y: r,
								page: c,
								width: a.width / this.scale,
								height: a.height / this.scale
							};
						alert(JSON.stringify(l))
					},
					scrool: function() {
						for (var t, e, a = (null === (t = this.$refs.contentDiv.firstElementChild) || void 0 === t || null === (e = t.getBoundingClientRect()) || void 0 === e ? void 0 : e.top) - 60, n = 0, i = 0, o = 0; o < this.$refs.contentDiv.childElementCount; o++) {
							var s, c;
							if (n += Math.abs(null === (s = this.$refs.contentDiv.children.item(o)) || void 0 === s ? void 0 : s.style.height.replace("px", "")) + Math.abs(null === (c = this.$refs.contentDiv.children.item(o)) || void 0 === c ? void 0 : c.style.marginBottom.replace("px", "")), Math.abs(a) < n) {
								i = o;
								break
							}
						}
						this.pageIndex = i + 1
					},
					showMore: function(t, e) {
						this.dialogFormVisible = !0, this.value = document.getElementById(e)
							.innerText, this.title = t
					},
					downPdf: function() {
						var t = window.location.search;
						t = decodeURIComponent(t), t = decodeURIComponent(t), t = t.replace("?file=", ""), window.location.href = t
					},
					plus: function() {
						this.screenWidth
					},
					minus: function() {
						this.screenWidth
					},
					prePage: function() {
						var t = document.getElementById("content"),
							e = t.children.item(this.pageIndex - 2);
						null === e || void 0 === e || e.scrollIntoView(!0), e && (this.pageIndex = this.pageIndex - 1)
					},
					firstPage: function() {
						var t = document.getElementById("content"),
							e = t.firstElementChild;
						null === e || void 0 === e || e.scrollIntoView(!0), e && (this.pageIndex = 1)
					},
					nextPage: function() {
						var t = document.getElementById("content");
						if (!(this.pageIndex >= t.childElementCount)) {
							var e = t.children.item(this.pageIndex);
							null === e || void 0 === e || e.scrollIntoView(!0), e && ++this.pageIndex
						}
					},
					demo: function(t) {
						this.urlToBlob(t)
					},
					lastPage: function() {
						var t = document.getElementById("content");
						this.pageIndex = t.childElementCount;
						var e = t.children.item(this.pageIndex - 1);
						null === e || void 0 === e || e.scrollIntoView(!0)
					},
					uploadFile: function() {
						this.isFont = !1, this.file = null, this.$refs.file.click()
					},
					fileChanged: function() {
						var t = this;
						this.file = this.$refs.file.files[0];
						var e = this.file.name.replace(/.+\./, "");
						if (-1 !== ["ofd"].indexOf(e))
							if (this.file.size > 104857600) this.$alert("error", "文件大小需 < 100M", {
								confirmButtonText: "确定",
								callback: function(e) {
									t.$message({
										type: "info",
										message: "action: ".concat(e)
									})
								}
							});
							else {
								var a = this,
									n = new FileReader;
								n.readAsDataURL(this.file), n.onload = function(t) {
									a.ofdBase64 = t.target.result.split(",")[1]
								}, this.getOfdDocumentObj(this.file, this.screenWidth), this.$refs.file.value = null
							}
						else this.$alert("error", "仅支持ofd类型", {
							confirmButtonText: "确定",
							callback: function(e) {
								t.$message({
									type: "info",
									message: "action: ".concat(e)
								})
							}
						})
					},
					getOfdDocumentObj: function(t, e) {
						(new Date)
						.getTime();
						var a = this;
						this.currentFile = t, this.loading = !0;
						var n = document.getElementById("content");
						n.innerHTML = "", it({
							ofd: t,
							success: function(t) {
								if (0 == t.code) {
									a.ofdObj = t.data.ofd;
									(new Date)
									.getTime();
									a.pageCount = a.ofdObj.Document[0].Pages.length;
									var n = ot(e, a.ofdObj.Document[0]);
									(new Date)
									.getTime(), (new Date)
										.getTime();
									a.displayOfdDiv(n), a.loading = !1
								} else console.log(t.msg)
							}
						})
					},
					displayOfdDiv: function(t) {
						this.scale = st();
						var e = document.getElementById("content");
						e.innerHTML = "";
						var a, n = Object(l["a"])(t);
						try {
							for (n.s(); !(a = n.n())
								.done;) {
								var i = a.value;
								e.appendChild(i)
							}
						} catch (r) {
							n.e(r)
						} finally {
							n.f()
						}
						var o, s = Object(l["a"])(document.getElementsByName("seal_img_div"));
						try {
							for (s.s(); !(o = s.n())
								.done;) {
								var c = o.value;
								this.addEventOnSealDiv(c, c.dataset.pageRef, c.dataset.signatureId)
							}
						} catch (r) {
							s.e(r)
						} finally {
							s.f()
						}
					},
					addEventOnSealDiv: function(t, e, a) {
						try {
							var n = this;
							t.addEventListener("click", (function() {
								n.dialogSignVisible = !0;
								var t = n.findStamp(e, a);
								if (t) {
									n.VerifyRet = "文件摘要值后台验证中，请稍等... ";
									var i, o = "",
										s = Object(l["a"])(t.SignedInfo.References);
									try {
										for (s.s(); !(i = s.n())
											.done;) {
											var c = i.value;
											o += "".concat(D(c.FileRef), "|")
										}
									} catch (r) {
										s.e(r)
									} finally {
										s.f()
									}
									nt({
										ofd: n.currentFile,
										list: o,
										method: t.SignedInfo.ReferencesCheckMethod,
										success: function(e) {
											var a, i = !0,
												o = Object(l["a"])(t.SignedInfo.References);
											try {
												for (o.s(); !(a = o.n())
													.done;) {
													var s = a.value,
														c = s.CheckValue,
														d = e["".concat(D(s.FileRef))];
													i &= c === d;
													var u = i ? "文件摘要值验证成功" : "文件摘要值验证失败";
													n.VerifyRet = "".concat(t.SignatureValid ? "签名值验证成功" : "签名值验证失败", ", ")
														.concat(u)
												}
											} catch (r) {
												o.e(r)
											} finally {
												o.f()
											}
										}
									})
								}
							}))
						} catch (i) {
							console.log(i)
						}
					},
					findStamp: function(t, e) {
						for (var a = null, n = 0; n < this.ofdObj.Document[0].Signatures.Signature.length; n++) {
							var i, o = this.ofdObj.Document[0].Signatures.Signature,
								s = Object(l["a"])(o);
							try {
								for (s.s(); !(i = s.n())
									.done;) {
									var c, r = i.value,
										d = Object(l["a"])(r.Signature.SignedInfo.StampAnnot);
									try {
										for (d.s(); !(c = d.n())
											.done;) {
											var u = c.value;
											if (u.ID == e && u.PageRef == t) {
												a = r.Signature;
												break
											}
										}
									} catch (S) {
										d.e(S)
									} finally {
										d.f()
									}
								}
							} catch (S) {
								s.e(S)
							} finally {
								s.f()
							}
						}
						if (!a) return null;
						this.spProvider = a.SignedInfo.Provider.ProviderName, this.spSignMethod = a.SignedInfo.SignatureMethod, this.spSignedValue = a.signatureHex, this.spHashedValue = a.HashHex, this.spSealID = a.SignedInfo.Seal.esID, this.spSealVersion = a.SignedInfo.Seal.Header.version, this.spSealName = a.SignedInfo.Seal.Property.name, this.spSealType = a.SignedInfo.Seal.Property.type, this.spSealMakeTime = a.SignedInfo.Seal.Property.createDate, this.spSealAuthTime = "".concat(a.SignedInfo.Seal.Property.validStart, " 至 ")
							.concat(a.SignedInfo.Seal.Property.validEnd);
						var f, p = D(a.SignCert.subject)
							.split("/"),
							h = Object(l["a"])(p);
						try {
							for (h.s(); !(f = h.n())
								.done;) {
								var v = f.value;
								if (0 === v.toLowerCase()
									.indexOf("ou")) {
									var g = v.substr(3, v.length);
									if (0 === g.indexOf("\\x")) {
										var m, y = T(g, "\\")
											.split("\\"),
											b = [],
											w = Object(l["a"])(y);
										try {
											for (w.s(); !(m = w.n())
												.done;) {
												var C = m.value;
												b.push("0".concat(C) - 0)
											}
										} catch (S) {
											w.e(S)
										} finally {
											w.f()
										}
										var x = k(b);
										this.spSigner = x
									} else this.spSigner = g;
									break
								}
							}
						} catch (S) {
							h.e(S)
						} finally {
							h.f()
						}
						return a
					},
					urlToBlob: function(t) {
						var e = new XMLHttpRequest;
						e.open("get", t, !0), e.responseType = "blob";
						var a = this,
							n = t.substring(t.lastIndexOf("/") + 1);
						e.onreadystatechange = function() {
							if (4 == e.readyState && 200 == e.status) {
								var t = new File([this.response], n, {
									type: "application/ofd",
									lastModified: Date.now()
								});
								setTimeout((function() {
									a.getOfdDocumentObj(t, a.screenWidth)
								}), 1500)
							}
						}, e.send()
					}
				}
			},
			rt = ct,
			lt = (a("a15e"), a("2877")),
			dt = Object(lt["a"])(rt, c, r, !1, null, "587ba1af", null),
			ut = dt.exports,
			ft = {
				name: "App",
				components: {
					HelloWorld: ut
				}
			},
			pt = ft,
			ht = (a("034f"), Object(lt["a"])(pt, o, s, !1, null, null, null)),
			vt = ht.exports,
			gt = (a("5717"), a("5c96")),
			mt = a.n(gt),
			yt = (a("0fae"), a("ecee")),
			bt = a("c074"),
			wt = a("ad3d"),
			Ct = a("bc3a"),
			xt = a.n(Ct);
		i["default"].prototype.$axios = xt.a, yt["c"].add(bt["a"]), i["default"].config.productionTip = !1, i["default"].component("font-awesome-icon", wt["a"]), i["default"].use(mt.a), new i["default"]({
				render: function(t) {
					return t(vt)
				}
			})
			.$mount("#app")
	},
	5717: function(t, e, a) {},
	"85ec": function(t, e, a) {},
	a15e: function(t, e, a) {
		"use strict";
		a("e7db")
	},
	e7db: function(t, e, a) {}
});
//# sourceMappingURL=app.4e3c4068.js.map