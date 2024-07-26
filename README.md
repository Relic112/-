![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/mumuy/relationship/test.yml)

由于工作生活节奏不同，如今很多关系稍疏远的亲戚之间来往并不多。因此放假回家过年时，往往会搞不清楚哪位亲戚应该喊什么称呼，很是尴尬。然而搞不清亲戚关系和亲戚称谓的不仅是小孩，就连年轻一代的大人也都常常模糊混乱。

“中国家庭称谓计算器”为你避免了这种尴尬，只需简单的输入即可算出称谓。输入框兼容了不同的叫法，你可以称呼父亲为：“老爸”、“爹地”、“老爷子”等等，方便不同地域的习惯叫法。快捷输入按键，只需简单的点击即可完成关系输入，算法还支持逆向查找称呼哦～！

项目演示地址：[https://passer-by.com/relationship/](https://passer-by.com/relationship/)

移动版演示地址: [https://passer-by.com/relationship/vue/](https://passer-by.com/relationship/vue/)

## 一、下载 & 安装

脚本库可以用于浏览器，也可以用于 Nodejs 环境中。

1. 在网页中引入 `<script src="https://passer-by.com/relationship/dist/relationship.min.js">`

   获取全局方法 `relationship`
   
2. 使用 npm 安装名为 `relationship.js ` 的包

	> **npm install relationship.js**

	在脚本中引入模块

	```js
	// CommonJS 引入
	const relationship = require("relationship.js");
	```
	```js
	// ES Module 引入
 	import relationship from 'relationship.js';
	```
 	 ![npm](https://img.shields.io/npm/v/relationship.js)  ![npm](https://img.shields.io/npm/dw/relationship.js)

## 二、使用
