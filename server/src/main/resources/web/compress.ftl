<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0">
	  <title>${file.name}压缩包文件预览</title>
    <link href="css/zTreeStyle.css" rel="stylesheet" type="text/css">
    <#include "*/commonHeader.ftl">
    <style type="text/css">
        body {
            background-color: #404040;
        }
        h1, h2, h3, h4, h5, h6 {color: #2f332a;font-weight: bold;font-family: Helvetica, Arial, sans-serif;padding-bottom: 5px;}
        h1 {font-size: 24px;line-height: 34px;text-align: center;}
        h2 {font-size: 14px;line-height: 24px;padding-top: 5px;}
        h6 {font-weight: normal;font-size: 12px;letter-spacing: 1px;line-height: 24px;text-align: center;}
        a {color:#3C6E31;text-decoration: underline;}
        
        code {color: #2f332a;}
        div.zTreeDemoBackground {width:600px;text-align:center;margin: 0 auto;background-color: #ffffff;}
		a:link{color: red;}  /*超链接默认样式*/
a:visited{color: blue;}  /*超链接被访问后的样式*/
a:hover{color: green;}   /*鼠标经过超链接的样式*/
a:active{color: yellow;}  /*超链接被激活时的样式*/
    </style>

</head>
<body>

<div class="zTreeDemoBackground left">
    <ul id="treeDemo" class="ztree"></ul>
	  <#list fileTree as img>
	<#if img?contains("http://") || img?contains("https://")>
    <#assign finalUrl="${img}">
<#else>
    <#assign finalUrl="${baseUrl}${img}">
</#if>

 <li><a href="javascript:void(0);" onclick="deleteFile('${finalUrl}')">${img}</a></li>
     
    </#list>
</div>
<script>
 function deleteFile(fileName) {
 window.open('${baseUrl}onlinePreview?url='+encodeURIComponent(Base64.encode(fileName)));
  
    }
</script>
</body>
</html>