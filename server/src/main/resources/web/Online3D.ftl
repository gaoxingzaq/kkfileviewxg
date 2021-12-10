<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0">
    <title>${file.name}3D预览</title>
	<script type="text/javascript" src="website/three.min.js"></script>
    <script type="text/javascript" src="website/o3dv.min.js"></script>
    <#include "*/commonHeader.ftl">
</head>

<script type='text/javascript'>
        OV.Init3DViewerElements ();
    </script>
<body>
  <div id="bigDiv" style="height:600px;" class="online_3d_viewer"
        model="${pdfUrl}"
        camera="3,1,2,0,0,0,0,0,1"
        defaultcolor="200,0,0"
        backgroundcolor="200,200,200"
        edgesettings="on,0,0,0,1">
    </div>
</body>
<script type="text/javascript">
  
var a=document.getElementById("bigDiv").style.height=window.innerHeight

    /*初始化水印*/
    window.onload = function () {
        initWaterMark();
    }
</script>
</html>