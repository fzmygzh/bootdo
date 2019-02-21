

//原文：https://blog.csdn.net/nangeali/article/details/71077834?utm_source=copy
//设置日期时间控件
function Datetime() {
    $('#datetimepicker1').datetimepicker({
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),
        autoclose: true,//选中自动关闭
        todayBtn: true,//显示今日按钮
        locale: moment.locale('zh-cn')
    });
    //默认获取当前日期
    var today = new Date();
    var nowdate = (today.getFullYear()) + "-" + (today.getMonth() + 1) + "-" + today.getDate();
//对日期格式进行处理
    var date = new Date(nowdate);
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var mydate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
    document.getElementById("nowdate").value = mydate;
    /*//默认获取当前日期
    var today = new Date();
    var nowdate = (today.getFullYear()) + "-" + (today.getMonth() + 1) + "-" + today.getDate();
    //对日期格式进行处理
    var date = new Date(nowdate);
    var mon = date.getMonth() + 1;
    var day = date.getDate();
    var mydate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
    document.getElementById("nowdate").value = mydate;*/
}

/*<a class='input-group date' id='datetimepicker1' style="float: left; left: 320px;">
    <input type='text' class="form-control" id='nowdate' style="width: 150px; height: 30px;" />
    <span class="input-group-addon" style="float: left; width: 50px; height: 30px;">
    <span class="glyphicon glyphicon-calendar"></span>
    </span>
    </a>*/
