"use strict";
//# sourceURL=newsedit.js

// DOM 加载完再执行
$(function () {
    var E = window.wangEditor;
    var editor = new E("#toolbar", '#editor');
    editor.customConfig.uploadImgServer = '../uploadImg';
    editor.customConfig.uploadFileName = 'img';
    editor.customConfig.uploadImgMaxSize = 1024 * 256;
    editor.customConfig.uploadImgMaxLength = 1;
    editor.customConfig.pasteIgnoreImg = true;
    var $text1 = $('#text1');
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $text1.val(html)
    };
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'image',  // 插入图片
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    editor.customConfig.colors = [
        '#000000',
        '#777777',
        '#1c487f',
        '#4d80bf',
        '#c24f4a',
        '#ffffff',
        '#8baa4a',
        '#7b5ba1',
        '#46acc8',
        '#f9963b'
    ];
    editor.customConfig.uploadImgHooks = {
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        }
    };
    editor.create();
    if (content !== null) {
        editor.txt.html(content);
    }
    // 初始化 textarea 的值
    $text1.val(editor.txt.html());
    // 发布
    $("#submitNews").click(function () {
        $.ajax({
            url: '/management/News/save',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "id": $('#newsId').val(),
                "title": $('#title').val(),
                "summary": $('#summary').val(),
                "content": $('#text1').val(),
                "newstype": $('#newstype').val()
            }),
            success: function (data) {
                if (data.success) {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                    parent.layui.table.reload("newsTable");
                    parent.layer.msg(data.message);
                } else {
                    layer.msg(data.message);
                }
            },
            error: function () {
                layer.msg("异常!未能成功提交!");
            }
        })
    });


});