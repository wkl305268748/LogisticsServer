/**
 * Created by WKL on 2017-7-16.
 */
function Customer(baseUrl) {
    this.baseUrl = baseUrl;
}

Customer.prototype = {
    constructor:Customer,
    add:function(){
        var url = this.baseUrl+"/v1/order/api/customer";

        $().ready(function(){
            $(".date-picker").datepicker({
                language: "zh-CN"
            });
            $('#target').citypicker();
        });
        const config = {
            errorBagName: 'errors', // change if property conflicts.
            delay: 0,
            locale: 'zh_CN',
            messages: null,
            strict: true
        };
        Vue.use(VeeValidate,config);
        new Vue({
            el: '#form',
            data: {
                order:{
                    send_name:"",
                    send_phone:"",
                    send_addr:"",
                    send_addr_info:"",
                    recive_phone:"",
                    recive_addr:"",
                    recive_addr_info:"",
                    dispatching_type:"dispatching",
                    send_time:"",
                    recive_time:"",
                    goods: [
                        {
                            name:"",
                            size:"",
                            weight:"",
                            remark:""
                        }
                    ]
                }
            },
            methods: {
                addNewGoods: function () {
                    this.order.goods.push({name:"",size:"",weight:"",remark:""});
                },
                deleteGoods: function (index) {
                    this.order.goods.splice(index,1);
                },
                submitOrder: function () {
                    var resouce = this.$resource(url);
                    var orders = this.order;
                    this.$validator.validateAll().then(function(result){
                        if(result)
                        {
                            var fd = new FormData();
                            fd.append('send_name',orders.send_name);
                            fd.append('send_phone',orders.send_phone);
                            fd.append('send_addr',orders.send_addr);
                            fd.append('send_addr_info',orders.send_addr_info);
                            fd.append('recive_phone',orders.recive_phone);
                            fd.append('recive_addr',orders.recive_addr);
                            fd.append('recive_addr_info',orders.recive_addr_info);
                            fd.append('dispatching_type',orders.dispatching_type);
                            fd.append('send_time',orders.send_time);
                            fd.append('recive_time',orders.recive_time);
                            fd.append('goods',JSON.stringify(orders.goods));

                            resouce.save(fd).then(
                                function (response){
                                    var result = $.parseJSON(response.bodyText);
                                    if(result.success)
                                    {
                                        alert('成功！');
                                    }
                                },
                                function (error){
                                    console.log(error);
                                }
                            );
                        }
                    });


                }
            }
        })
    }
}