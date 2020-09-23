function initPage(typeDiv,brandDiv) {
    $.ajax({
        url:"/api/product/typeBrand",
        type:"get",
        success:function (rs) {
            var htmlBrand = "";
            var htmlType = "";
            var list1 = rs[0];
            var list2 = rs[1];
            for (var i = 0; i <list1.length ; i++) {
                htmlBrand +="<option value='"+list1[i]+"'>"+list1[i]+"</option>";
            }
            for (var i = 0; i <list2.length ; i++) {
                htmlType +="<option value='"+list2[i]+"'>"+list2[i]+"</option>";
            }
            $("#"+brandDiv).html(htmlBrand);
            $("#"+typeDiv).html(htmlType);
        }
    })
}

function initRoles(roleDiv) {
    $.ajax({
        url:"/api/roles",
        type:"get",
        success:function (info) {
            var html = "";
            for (var i = 0; i <info.length ; i++) {
                html +=" <li><i class=\"fa fa-users green\"></i> <a href=\"#\">"+info[i].roleName+"</a></li>";
            }
            $("#"+roleDiv).html(html);
        }
    })
}