/**
 * 界面js封装
 * 付乐天
 */
var $window;
var $window2;
var enableWinCount=0;//显示状态的window计数
var $globalGrid;//只有一个grid同时存在
var common={}
//===============================关于jQgrid========================================
/**
 * gridMap.gridSelector //grid容器（table标签的id）
 * gridMap.dataUrl 		//grid加载的数据的url
 * gridMap.caption		//grid标题
 * gridMap.pager		//导航栏selector
 * gridMap.reloadUrl	//双击单条记录时通过此记录id获取加载整条记录
 * gridMap.onEvent.dbclickWindowTitle	//双击记录时弹出窗口的标题
 * gridMap.searchInputs		//查询相关input、select，会自动获取其name属性作为可以，val()作为value，发送到后台action中
 */
common.renderJqGrid=function(gridMap){
	//grid准备数据
	var $grid=$(gridMap.gridSelector);
	var param=$.extend({
		dbclickWinWidth:300,
		dbclickWinHeigth:200
	},gridMap);
	var rows=20;//默认分页大小
	if(gridMap.rows){
		rows=gridMap.rows;
	}
	$grid.jqGrid({
	   	url:gridMap.dataUrl,
		datatype: "json",
	   	colNames:gridMap.colNames,
	   	colModel:gridMap.colModel,
	   	rowNum:rows,
	   	//rowList:[10,20,30],
	   	autowidth:true,
	    height:'100%',
	   	pager: gridMap.pager,
	   	cmTemplate:{sortable:false},
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
//	    caption:gridMap.caption,
	    multiselect: true,
	    jsonReader:{
	    	root:'rows',
	    	repeatitems: true
	    },
	    ondblClickRow:function(rowid,iRow,iCol,e){
	    	if(gridMap.onEvent){
	    		if(gridMap.onEvent.dbclickWindowTitle){
	    			common.openWindow(gridMap.onEvent.dbclickWindowTitle,gridMap.reloadUrl+rowid,param.dbclickWinWidth,param.dbclickWinHeight);
	    		}
	    	}
	    	
	    },
		gridComplete: function(){
			if(gridMap.gridComplete){
				gridMap.gridComplete();
			}
		},
	    beforeRequest:function(){
//	    	if(!isFirst){
//		    	if(gridMap.searchInputs){
//		    		var inputData={};
//		    		$.each(gridMap.searchInputs,function(index,input){
//		    			inputData[$(input).attr("name")]=$(input).val();
//		    		});
//			    	$grid.jqGrid("setGridParam",{
//			    		postData:inputData
//			    	});
//		    	}
//	    	}
//
//	    	isFirst=false;
	    	
	    	if(gridMap.searchInputs){
	    		var inputData={};
	    		$.each(gridMap.searchInputs,function(index,input){
	    			console.log($(input).attr("name")+"="+$(input).val());
	    			inputData[$(input).attr("name")]=encodeURI($(input).val());
	    		});
		    	$grid.jqGrid("setGridParam",{
		    		postData:inputData
		    	});
	    	}
	    }
	});
	$grid.jqGrid('navGrid',gridMap.pager,{edit:false,add:false,del:false,search:false,refresh:false});
	
	$globalGrid=$grid;
	return $grid;
}

/**
 * 刷新jqGrid
 */
common.reloadGrid=function(id){
	//刷新表格
	if(id){
		if(id.indexOf("#")<0){
			id="#"+id;
			$(id).jqGrid('setGridParam',{  
		        page:$(id).jqGrid("getGridParam","page")  
		    }).trigger("reloadGrid");
		}else{
			$(id).jqGrid('setGridParam',{  
		        page:$(id).jqGrid("getGridParam","page")  
		    }).trigger("reloadGrid");
		}
		
	}else{
		$globalGrid.jqGrid('setGridParam',{  
	        page:$globalGrid.jqGrid("getGridParam","page")  
	    }).trigger("reloadGrid");
	}
}
/**
 * 更新每一条选中数据的信息
 * 将data参数和选中的ids一并作为参数发送至url，成功返回后刷新表格
 * gridSelector 补偿参数，表格的selector
 */
common.updateGrid=function(url,data,gridSelector){
	var $grid;
	if(gridSelector){
		$grid=$(gridSelector);
	}else{
		$grid=$globalGrid;
	}
	var ids= $grid.jqGrid('getGridParam','selarrrow');
	if(ids.length==0){
		return false;
	}
	
	$.ajax({
		url:url+"?map.ids="+ids,
		data:data,
		async:false,
		success:function(data){
			//刷新表格
			$grid.jqGrid('setGridParam',{  
		        page:$globalGrid.jqGrid("getGridParam","page")
		    }).trigger("reloadGrid");
		}
	});
	
	return true;
}
/**
 * 传入查询参数，刷新表格
 * data[key-value]，条件约束
 */
common.reloadGridWithParam=function(data){
	$globalGrid.jqGrid("setGridParam",{
		postData:data,
		page:$globalGrid.jqGrid("getGridParam","page")
	}).trigger("reloadGrid");
}
/**
 * 执行jqGrid的删除动作，将jqgrid选中的id作为参数发送到指定url,并刷新grid
 * 
 * $grid ：初始化grid时返回的grid对象
 * 请求url格式如  '【delete.action】?ids=1,2,3',【】内容为配置指定，'?ids=1,2,3'为自动添加
 */
common.deleteJqGridSelectedRecord=function($grid,deleteUrl){
	var ids= $grid.jqGrid('getGridParam','selarrrow');
	if(ids.length==0){
		$().toastmessage('showWarningToast', "请选择删除项.");
	}else{
		var htmlTemplate='<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>确定要删除吗？</p>';
	    $( "#dialog-delete" ).html(htmlTemplate).dialog({
	        resizable: false,
	        height:160,
	        modal: true,
	        buttons: {
	          "确认": function() {
	            $( this ).empty().dialog( "close" );
	    		$.ajax({
	    			url:deleteUrl+"?map.ids="+ids,
	    			success:function(data){
	    				var dataStr = eval("("+data+")");
	    				if(dataStr.success){
	    					$().toastmessage('showSuccessToast', dataStr.msg);
	    					
	    					//刷新表格
	    					$globalGrid.jqGrid('setGridParam',{  
	    				        page:1
	    				    }).trigger("reloadGrid");
	    				}else{
	    					$().toastmessage('showErrorToast', dataStr.msg);
	    				}
	    			}
	    		});
	          },
	          "取消": function() {
	            $( this ).empty().dialog( "close" );
	          }
	        }
	      });
	}
}

//==================================关于window====================================================
/**
 * title 窗口标题
 * url 窗口请求的叶面
 */
common.openWindow=function(title,url,width,height){
	var w=600;
	var h=300;
	if(width){
		w=width;
	}
	if(height){
		h=height;
	}
	if(enableWinCount<1){
		$window=$("#window");
		$window.find("#windowData").empty().load(url);
		$window.dialog({
			title:title,
			width:w,
			height:h,
			modal:true,
			open:function(){
				enableWinCount++;
			},
			close:function(){
				enableWinCount--;
			}
		});		
	}else{
		$window2=$("#window2");
		$window2.find("#windowData2").empty().load(url);
		$window2.dialog({
			title:title,
			modal:true,
			width:w,
			height:h,
			open:function(){
				enableWinCount++;
			},
			close:function(){
				enableWinCount--;
			}
		});	
	}

//	if(enableWinCount==0){
//		$window=$("#window");
//		
//		$window.find("#windowData").empty().load(url);
//		
//		if(!$window.data("kendoWindow")){
//			$window.kendoWindow({
//				minWidth:"600px",
//				minHeight:"200px",
//				close:function(){
//					enableWinCount--;
//				},
//				modal:true,
//				title:title
//			});
//			$window.data("kendoWindow").center();
//			$window.data("kendoWindow").open();
//			enableWinCount++;
//		}else{
//			$window.data("kendoWindow").open();
//			$window.data("kendoWindow").title(title);
//			enableWinCount++;
//		}
//		
//		return $window;
//	}else if(enableWinCount==1){
//		$window2=$("#window2");
//		
//		$window2.find("#windowData2").empty().load(url);
//		
//		if(!$window2.data("kendoWindow")){
//			$window2.kendoWindow({
//				minWidth:"300px",
//				minHeight:"150px",
//				close:function(){
//					enableWinCount--;
//				},
//				modal:true,
//				title:title
//			});
//			$window2.data("kendoWindow").center();
//			$window2.data("kendoWindow").open();
//			enableWinCount++;
//		}else{
//			$window2.data("kendoWindow").open();
//			$window2.data("kendoWindow").title(title);
//			enableWinCount++;
//		}
//		
//		return $window2;
//	}
}
/*关闭窗口,确保关闭的窗口是common.openWindow（）初始化的*/
common.closeWindow=function(){
//	try{
//		$window2.data("kendoWindow").close();
//	}catch(error){
//		try{
//			$window.data("kendoWindow").close();
//		}catch(error){
//			alert("关闭窗口时错误，请确认此窗口是调用common下方法初始化的");
//		}
//	}
	
	//enableWinCount--;
	
	if(enableWinCount==2){
		$window2.dialog("close").find("#windowData2").empty();
	}else{
		$window.dialog("close").find("#windowData").empty();
	}

}
common.resizeWindow=function(width,height){
	if(enableWinCount==2){
		$window2.dialog("option",{width:width,height:height});
	}else{
		$window.dialog("option",{width:width,height:height});
	}
}
/*替换window里的内容
 * title	-新标题
 * url		--获取界面的路径
 * */
common.replaceWindow=function(title,url){
	$window.find("#windowData").load(url);
}

//===============================关于ajax===========================================

/*保存数据带参数请求url
 * url		=请求地址
 * data	==参数，【key-value】
 * success	=url返回‘success’代表成功，执行此方法
 * */
common.saveRecord=function(url,data,success){
	$.ajax({
		url:url,
		data:data,
		success:function(data){
			var dataStr = eval("("+data+")")
			if(dataStr.success){
				
				$().toastmessage('showSuccessToast', dataStr.msg);
			}else{
				$().toastmessage('showErrorToast', dataStr.msg);
			}
		}
	});
}
common.saveData=function(url,data,success,successText,errorText){
	$.ajax({
		url:url,
		data:data,
		async:false,
		success:function(data){
			var dataStr = eval("("+data+")");
			if(dataStr.success){
				
				$().toastmessage('showSuccessToast', dataStr.msg);
			}else{
				$().toastmessage('showErrorToast', dataStr.msg);
			}
		}
	});
}

//=========================================================================
common.renderTable=function(selector){
	var $trs=$(selector).addClass("ui-widget ui-widget-content").find("tr");
	//$trs.find("td:even").addClass("ui-state-default");
	//$trs.find("td:odd").addClass("ui-state-hover");
	$trs.find("td").addClass("ui-state-hover");
}
common.renderButton=function(){
	$(".button").button();
}
common.renderSearchHeader=function(id){
	var $div1=$(id);
	$div1.addClass("ui-state-highlight ui-corner-all").find("div").addClass("search-row");
	var html=$div1.html();
	$div1.empty().append("<div class='searchwidth'>"+html+"</div>")
}
common.renderMonthSelect=function(selector){
	var $selector=$(selector);
	var now=new Date();
	var month=now.getMonth()+1;
	var html="";
	for(var i=1;i<=12;i++){
		if(i==month){
			html+="<option value="+i+" selected>"+i+"</option>";
		}else{
			html+="<option value="+i+">"+i+"</option>";
		}
	}
	
	$selector.append(html);
}
$(function(){
	$(".button").button();
});
