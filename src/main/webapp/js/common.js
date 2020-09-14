function render() {

    layui.use(['table','form','layer'], function (obj) {
        var table = layui.table;
        var layer = layui.layer;
        var form = layui.form;

        form.render();
        //第一个实例
        table.render({
            elem: '#route-table'
            , toolbar:'#route-head-bar'
            , height: 312
            , url: '/route/table' //数据接口
            , page: {'limits':[5,10,15,20,25]} //开启分页
            , cols: [[ //表头
                {checkbox: true}
                , {field: 'id', title: 'ID', width: 80}
                , {field: 'username', title: '用户名', width: 177}
                , {field: 'password', title: '密码', width: 80}
                , {field: 'nickname', title: '昵称', width: 80}
                , {field: 'money', title: '余额', width: 177}
                , {field: 'address', title: '地址', width: 177}
                , {field: 'state', title: '状态', width: 80, templet:function (data) {
                        return (data.state===1)?'<span class="layui-badge layui-bg-green">有效</span>':
                            '<span class="layui-badge layui-bg-red">无效</span>';
                    }}
                , {field: 'right', title: '操作' ,width: '130' ,toolbar:'#route-route-bar'}
            ]]
        });
        /*  监听头工具栏事件
          * 语法：table.on('event(filter)', callback);
          * 注：event为内置事件名，filter为容器lay-filter设定的值。
          * table模块在Layui事件机制中注册了专属事件，如果你使用layui.onevent()自定义模块事件，请勿占用table名。
          */
        table.on("toolbar(route-table)",function (obj) {
            // obj为toolbar单击事件的event名称
            // console.log(obj)
            switch (obj.event) {
                case 'goEdit':
                    showEditWindow(null);
                    break;
                case 'delete':
                    var checkStatus = table.checkStatus("route-table").data;
                    if (checkStatus.length>0){
                        layer.confirm('真的删除行么', function(index) {
                            // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            var params = "";//存要删除的行标

                            /*将行标循环添加进参数里*/
                            $.each(checkStatus,function (index,object) {
                                params += "&ids="+object.id;
                            })
                            let params1 = params.slice(1,params.length)

                            //向服务端发送删除指令
                            $.post("/route/delete",params1,function (data) {
                                if (data.status){
                                    table.reload('route-table',{});
                                }else {
                                    alert(data.message)
                                }
                            });
                            layer.close(index)
                        });
                    }
                    break;
                case 'edit-route':
                    break;
            }
        });

        /*监听行工具事件
        * 这里的obj为行对象
        * obj.data为行内的数据*/
        table.on("tool(route-table)",function (obj) {
            console.log(obj.data);
            switch (obj.event) {
                case 'edit-route':
                    showEditWindow(obj.data);
                    break;
            }
        });
    });
}


/*添加和修改操作*/
function showEditWindow(data) {
    layer.open({
        type:1,
        title:"编辑数据",
        area:['500px', '350px'],
        content:data===null?$("#route-add-layer").html():$("#route-edit-layer").html(),
        btn:['提交','取消'],
        yes:function (index,layero) {
            /*请求后台做更新或添加操作*/
            $.ajax({
                type:"post",
                url:"/route/"+(data===null?'add':'update'),
                data:data===null?$("#route-add-form").serialize():$("#route-edit-form").serialize(),
                success:function (data) {
                    /*后端成功后的操作*/
                    if(data.status){
                        //更新成功,刷新表格
                        table.reload("route-table",{});
                        //关闭弹出框
                        layer.close(index);
                    }else {
                        //提示信息
                        alert(data.message);
                    }
                    layer.closeAll("loading")
                }
            })
        },
        /*层弹出成功后的回调函数*/
        success:function (layero,index) {
            console.log(data)
            //刷新form表单
            form.render();
            if (data!=null){
                form.val("route-edit-form",data)
            }
        }
    })
}

function getNames(obj,id) {
    $.ajax({
        url:'/customer/getcus',
        type: 'get',
        method: 'GET',
        success:function (result) {
            console.log(result)

            for (let i=0;i<result.length;i++){
                $(id).append(`<option value="${result[i].id}">${result[i].username}</option>`);
            }
            obj.render();
        }
    })
}

/*毫秒转日期*/
function getMyDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds();
    return oYear + '-' + addZero(oMonth) + '-' + addZero(oDay) + ' ' + addZero(oHour) + ':' +
        addZero(oMin) + ':' + addZero(oSen);
}
//补零操作
function addZero(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

function getMyDate2(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate();
    return oYear + '-' + addZero(oMonth) + '-' + addZero(oDay);
}
