<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顯示人員名單</title>
<style type="text/css"></style>
</head>
<link rel="stylesheet" href="common/js/jquery/plugins/jqgrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="common/js/jquery/plugins/jquery-ui/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
<link rel="stylesheet" href="common/css/demo.css">
<script src="common/js/jquery/jquery-1.8.3.js"></script>
<script src="common/js/jquery/plugins/jqgrid/js/jquery.jqGrid.min.js"></script>
<script src="common/js/jquery/plugins/jquery-ui/ui/jquery.ui.position.js"></script>
<script src="common/js/jquery/plugins/jquery-ui/ui/jquery.ui.core.js"></script>
<script src="common/js/jquery/plugins/jquery-ui/ui/jquery.ui.widget.js"></script>
<script src="common/js/jquery/plugins/jquery-ui/ui/jquery.ui.tabs.js"></script>
<script src="common/js/jquery/plugins/jquery-ui/js/jquery-ui-1.9.2.custom.js"></script>
<script src="common/js/jquery/plugins/jqgrid/js/i18n/grid.locale-cn.js"></script>
<style>
	#dialog label, #dialog input { display:block; }
	#dialog label { margin-top: 0.5em; }
	#dialog input, #dialog textarea { width: 95%; }
	#tabs { margin-top: 1em; }
	#tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
	#add_tab { cursor: pointer; }
	.divs{
		height: 100%;
		width: 100%;
	}
</style>
<script type="text/javascript">
	$(function(){
		
		jQuery("#list1").jqGrid({
	        treeGrid: true,
	        treeGridModel: "adjacency",
	        ExpandColumn: "菜单",
	        ExpandColClick: true,
	        url: "tree.json",
	        datatype: "json",
	        colNames: ["menu","des", "url"],
	        colModel: [
	       	        {name: "menu",index: "menu",hidden: false}, 
	       	        {name: "des",index: "des",hidden: true}, 
	       	        {name: "url",index:"url",hidden: true}
	       	        ],
	        pager: false,
	        height:"auto",
	        width:"25%",
	        viewrecords: true,
	        caption:"首页",
	        jsonReader: { 
				repeatitems: true,
                root: "rows", 
                id:"id",
                cell:"cell",
                total: "total"
            },
			onSelectRow: function(id){
				var ret = jQuery("#list1").jqGrid('getRowData',id);
				var des = ret.des;
				var url = ret.url;
				if(des && url != ""){
					addTab(des,url);
				}
			}
	    });

		tabTemplate = "<li><a href='#[href]'>#[label]</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
		tabCounter = 2;

		var tabs = $( "#tabs" ).tabs();
		//添加界面
		function addTab(tabTitle,tabContent) {
			var label = tabTitle,
				id = "tabs-" + tabCounter,
				li = $( tabTemplate.replace( /#\[href\]/g, "#" + id ).replace( /#\[label\]/g, label ) ),
				tabContentHtml = tabContent;

			tabs.find( ".ui-tabs-nav" ).append( li );
			tabs.append( "<div id='" + id + "'><iframe width='100%' height='100%' frameborder='0' align='middle' scrolling='no' marginheight='0' marginwidth='0' src=" + tabContentHtml + "/></div>" );
			tabs.tabs( "refresh" );
			show(id);
			tabCounter++;
		}
		//貌似是删除
		$( "#tabs span.ui-icon-close" ).live( "click", function() {
			var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
			$( "#" + panelId ).remove();
			tabs.tabs( "refresh" );
		});

		function show(id){
			tabs.find(".ui-tabs-panel.list").css("display", "none");
			$("li[role='tab'].list").attr("aria-selected","false");
			$("li[aria-controls='"+id+"']").attr("aria-selected","true");
			$("#"+id).css("display", "true");
		}
	});
</script>
<body style="height: 100%;width: 100%;">
	<div id="tabs" style="width: 70%;float: right;">
		<ul>
			<li><a href="#tabs-1">首页</a> <span class="ui-icon ui-icon-close">Remove Tab</span></li>
		</ul>
		<div id="tabs-1">
			<p>不要在意细节</p>
		</div>
	</div>
	<div id="tree" style="width: 25%;">
		<table id="list1"></table>
		<div id="pager"></div>
	</div>
</body>
</html>