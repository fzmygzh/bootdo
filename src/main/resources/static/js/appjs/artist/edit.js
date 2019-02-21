$().ready(function () {
    loadArtistType();
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        var noName = document.getElementById("noName").style.display;
        var noArtName = document.getElementById("noArtName").style.display;
        var noPhone = document.getElementById("noPhone").style.display;
        var noEmils = document.getElementById("noEmils").style.display;
        var noPlateNum = document.getElementById("noPlateNum").style.display;
        var noAge = document.getElementById("noAge").style.display;
        if (noName == "none" && noArtName == "none" && noPhone == "none" && noEmils == "none" && noPlateNum == "none" && noAge == "none") {
            alert("success");
            update();
        }else{
            alert("请仔细检查填写的艺术家信息!!");
        }
    }
});

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/artist/info/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入名字"
            }
        }
    })
}

/*加载艺术类型的JS*/
function loadArtistType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/artisttype',
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight: 200
            });
            //点击事件
            $('.chosen-select').on('change', function (e, params) {
                console.log(params.selected);
                var opt = {
                    query: {
                        type: params.selected,
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            });
        }
    });
}

//用于form表单各项信息的验证，
function checkArtistName() {
    var artistname = document.getElementById("artistname").value;
    if (artistname != null && artistname != "") {
        document.getElementById("noName").style.display = "none";
        return artistname;
    }
    document.getElementById("noName").style.display = "inline";
    return null;
}

function checkArtistartName() {
    var artistartname = document.getElementById("artistartname").value;
    if (null != artistartname && artistartname != "") {
        document.getElementById("noArtName").style.display = "none";
        return artistartname;
    }
    document.getElementById("noArtName").style.display = "inline";
    return null;
}

function checkArtistPhone() {
    var artistphone = document.getElementById("artistphone").value;
    var reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    if (artistphone != null && artistphone != "") {
        if (!reg.test(artistphone)) {
            document.getElementById("noPhone").style.display = "inline";
            return null;
        }
        document.getElementById("noPhone").style.display = "none";
        return "1";
    }
    return "2";
}

function checkArtistEmil() {
    var artistemil = document.getElementById("artistemil").value;
    var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (artistemil != null && artistemil != "") {
        if (!reg.test(artistemil)) {
            document.getElementById("noEmils").style.display = "inline";
            return null;
        }
        document.getElementById("noEmils").style.display = "none";
        return "1";
    }
    return "2";
}

function checkArtistPlateNumber() {
    var artistplatenumber = document.getElementById("artistplatenumber").value;
    var reg = /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/;
    if (artistplatenumber != null && artistplatenumber != "") {
        if (!reg.test(artistplatenumber)) {

            document.getElementById("noPlateNum").style.display = "inline";
            return null;
        }
        document.getElementById("noPlateNum").style.display = "none";
        return "1";
    }
    return "2";
}

function checkArtistAge() {
    var artistage = document.getElementById("artistage").value;
    var reg = /^[1-9]\d*$/;
    if (artistage != null && artistage != "") {
        if (!reg.test(artistage)) {
            document.getElementById("noAge").style.display = "inline";
            return null;
        }
        document.getElementById("noAge").style.display = "none";

        return "1";
    }
    return "2";

}