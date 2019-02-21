var prefix = "/artistpage"
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        checkbox : true
                    },

                    {
                        field : 'artistname',
                        title : '姓名'
                    },
                    {
                        field : 'artistartname',
                        title : '艺名'
                    },
                    {
                        field : 'artistphone',
                        title : '联系方式'
                    },
                    {
                        field : 'artistemil',
                        title : '邮箱'
                    },
                    {
                        field : 'artistplatenumber',
                        title : '车牌号'
                    },

                    {
                        field : 'artistsex',
                        title : '性别',
                        formatter : function(value, row, index){
                            if(value==0){
                                return '<span>男</span>';
                            }else if(value==1){
                                return '<span>女</span>';
                            }else if(value==2){
                                return '<span>未知</span>';
                            }
                        }
                    },

                    {
                        field : 'artisttype',
                        title : '艺术类型',
                        formatter : function(value, row, index){
                            if(value==0){
                                return '<span>美术家</span>';
                            }else if(value==1){
                                return '<span>收藏家</span>';
                            }
                        }
                    },

                    {
                        field : 'artistpic',
                        title : '头像',
                        formatter : function(value, row, index){
                            if(value==null||value==""){
                                return '<span><img  height="60" width="60" onclick="changepic(\''+row.id+'\')" src="/img/moren.jpg"/></span>';
                            }else if(value!=null||value!=""){
                                return '<span><img  height="60" width="60" onclick="changepic(\''+row.id+'\')" src="'+value+'"/></span>';
                            }
                        }
                    },
                    {
                        field : 'artwork',
                        title : '艺术链接',
                        formatter : function(value, row, index){
                            if(value==null||value==""){
                                return '<span><img  height="60" width="60" onclick="artistImgList(\''+row.id+'\')" src="/img/moren.jpg"/></span>';
                            }else if(value!=null||value!=""){
                                return '<span><img  height="60" width="60" onclick="artistImgList(\''+row.id+'\')" src="/img/moren.jpg"/></span>';
                            }
                        }

                    },
                    {
                        field : 'artistindate',
                        title : '入驻时间'
                    },
                    {
                        field : 'artstate',
                        title : '状态',
                        formatter : function(value, row, index){
                            if(value==0){
                                return '<span class="label label-primary">启用</span>';
                            }else if(value==1){
                                return '<span class="label label-danger">停用</span>';
                            }
                        }
                    },
                   ]
            });
}
function artistImgList(id){
    layer.open({
        type:2,
        title:'作品集',
        maxmin:true,
        shadeClose:false,
        area:['800px','520px'],
        content:prefix+'/artistFile/'+id

    });

}