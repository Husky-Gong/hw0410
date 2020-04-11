<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2020/4/10
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Bank System</title>
    <base href="<%=request.getContextPath()+"/"%>">

    <script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>

    <script type="text/javascript">

        var flag1=false;
        var flag2=false;
        var flag3=false;

        $(function () {

            /******提交表单的总体校验****/
            $("form").submit(function () {
                return flag1&&flag2&&flag3;
            })


            /**********校验收款账号信息*****/
            $("#inAcc").blur(function () {

                //获得收款人卡号
                var  inAcc=$("#inAcc").val();

                //获得转账人卡号
                var  outAcc=$("#outAcc").val();

                if(outAcc==inAcc){
                    alert("Wrong");
                }else{

                    $.post("zx/AccountServlet?service=checkInAcc",{inAcc:inAcc},function (result) {

                        if(result){
                            $("#span_inAcc").html("legal").css("color","green");
                            flag1=true;
                        }else {
                            $("#span_inAcc").html("illegal").css("color","red");
                        }

                    },"json");

                }
            })

            /**********金额的校验*********/
            $("#money").blur(function () {
                var outAcc=$("#outAcc").val();

                var pwd=$("#pwd").val();

                var money =$("#money").val();

                $.post("zx/AccountServlet?service=checkMoney",{outAcc:outAcc,pwd:pwd,money:money},function (result) {

                    if(result){

                        $("#span_money").html("Enough").css("color","green");
                        flag2=true;
                    }else {
                        $("#span_money").html("Not enough").css("color","red");
                    }

                },"json")


            })


            /********卡号的校验**********/
            $("#pwd").blur(function () {

                var outAcc=$("#outAcc").val();

                var pwd=$("#pwd").val();

                if(outAcc!=""&&pwd!=""){
                    //发送ajax请求进行信息校验
                    $.post("zx/AccountServlet?service=checkOutAcc",{outAcc:outAcc,pwd:pwd},function (result) {

                        if(result){

                            $("#span_pwd").html("Legal").css("color","green");
                            flag3=true;
                        }else {
                            $("#span_pwd").html("Illegal").css("color","red");
                        }

                    },"json")

                }else{
                    alert("Wrong");
                }


            })

        })


    </script>
</head>
<body>


<div style="width: 500px;margin: 0px auto;" >
    <h3 align="center">Bank System</h3>
</div>


<hr/>
<div style="width: 400px;border:  0px solid  red;margin-left: 300px">

    <form action="zx/AccountServlet?service=transfer" method="post">
        <p>
            OutAcc:<input type="text" name="outAcc" id="outAcc"/>${requestScope.msg}
        </p>
        <p>
            Password:<input type="text" name="pwd" id="pwd"/><span id="span_pwd"></span>
        </p>
        <p>
            Money:<input type="text" name="money" id="money"/><span id="span_money"></span>
        </p>
        <p>
            InAcc:<input type="text" name="inAcc" id="inAcc"/><span id="span_inAcc"/>
        </p>
        <p>
            <input type="submit" value="Confirm"/>
        </p>
    </form>
</div>

</body>
</html>
