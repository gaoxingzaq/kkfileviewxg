<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>${file.name}Tiff 图片预览</title>
    <link rel="stylesheet" href="css/viewer.min.css">
    <script src="js/tiff.min.js"></script>
    <#include "*/commonHeader.ftl">
    <style>
    <style>
    * {
        margin: 0;
        padding: 0;
    }

    html, body {
        height: 100%;
        width: 100%;
    }

</style>
</head>
<body>
<input hidden id="currentUrl" value="${currentUrl}"/>
<div id="tiff">
</div>

<script>

	
	$(function () {
  Tiff.initialize({TOTAL_MEMORY: 16777216 * 10});
  var xhr = new XMLHttpRequest();
  xhr.open('GET', $("#currentUrl").val());
  xhr.responseType = 'arraybuffer';
  xhr.onload = function (e) {
    var buffer = xhr.response;
    var tiff = new Tiff({buffer: buffer});
    for (var i = 0, len = tiff.countDirectory(); i < len; ++i) {
      tiff.setDirectory(i);
      var canvas = tiff.toCanvas();
      $('body').append(canvas);
    }
  };
  xhr.send();
});

    /*初始化水印*/
    window.onload = function () {
        initWaterMark();
    }
</script>
</body>

</html>
