/**
 * 创建Select4组件
 * @param {Object} multiple    是否多选
 * @param {Object} height    高
 * @param {Object} defaultVal    默认值如： '["0","0","0"]'    '["0"]'    '02002'    '0'    0
 * @param {Object} placeholder    holder
 * @param {Object} item_callback    单个元素的点击回调事件
 * @param {Object} data        列表数据
 * @param {Object} readonly    是否只读
 * @param {Object} selectParent    是否支持选择父节点
 * @param {Object} duplicate    是否支持单个元素多选
 */

$.fn.createSelect4 = function (multiple, height, defaultVal, placeholder, item_callback, data, readonly, selectParent, duplicate) {
    if (this.length == 0) {
        return;
    }
    if (!readonly) {
        this.attr('readonly', true);
    }
    if (!this.attr('link')) {
        var list = [];
        try {
            list = JSON.parse(this.attr('list'));
        } catch (e) {
            console.warn(e)
        }
        $('#' + this.attr('id')).select4({
            treeData: list,
            isMultiple: multiple ? multiple : false,
            width: '220px',
            height: '34px',
            placeholder: placeholder,
            defaultVal: defaultVal,
            item_callback: item_callback,
            readonly: readonly,
            selectParent: selectParent,
            duplicate: duplicate
        });
    } else {
        var self = this, para = {
            product: this.data('product'),
            action: this.data('action'),
            subAction: this.data('subaction'),
            data: data ? data : '{}'
        };

        function dataError(err) {
            self.parent().append("<div class='form-control err word-inline' title='" + err + "'>" + err + "</div>")
            self.remove();
        }

        $.get('' + this.attr('link'), para, function (json) {
            if (json && json.code == '000') {
                if (json.data && json.data.rspCode == '0000') {
                    $('#' + self.attr('id')).select4({
                        treeData: json.data.nodes,
                        isMultiple: multiple ? multiple : false,
                        width: '220px',
                        height: (height ? height : 34) + 'px',
                        placeholder: placeholder,
                        defaultVal: defaultVal,
                        item_callback: item_callback,
                        readonly: readonly,
                        selectParent: selectParent,
                        duplicate: duplicate
                    });
                } else if (json.data) {
                    dataError('初始化失败，' + json.data.rspDesc)
                    console.log('初始化失败，' + json.data.rspDesc)
                } else {
                    dataError('初始化失败，数据无效')
                    console.log('初始化失败，数据无效')
                }
            } else if (json) {
                dataError('初始化失败，' + json.desc)
                console.log('初始化失败，' + json.desc)
            } else {
                dataError('初始化失败，接口返回空值')
                console.log('初始化失败，接口返回空值')
            }
        }, "json");
    }
}