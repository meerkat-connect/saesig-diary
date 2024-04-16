class DataTablesHelper {
    tableId = null;
    contentPanelId = null;
    $table = null;
    $contentPanel = null;

    constructor(tableId, contentPanelId) {
        this.tableId = tableId;
        this.contentPanelId = contentPanelId;
        this.$table = $('#' + tableId);
        this.$contentPanel = $('#' + contentPanelId);
    }

    datatables = (customOption) => {
        const self = this;

        const option = {
            columnDefs: [
                {className: "dt-center", targets: "_all"}
            ],
            stripeClasses: [],
            pagingType: 'full_numbers',
            lengthChange: false,
            responsive: true,
            searching: false,
            destroy: true,
            ordering: false,
            paging: true,
            info: false,
            pageLength: 10,
            bPaginate: true,
            bAutoWidth: true,
            serverSide: true,
            processing: true,

            ajax: {
                async: false,
                url: customOption.url,
                method: 'get',
                data: function (d) {
                    return self.setRequestData(customOption.searchParameters, d);
                },
                "dataSrc": function (res) {
                    res.data.forEach(function (data, index) {
                        data.rowNumber = res.recordsTotal - (res.pageNumber * res.pageSize) - index;
                    });
                    return res.data;
                }
            },

            language: {
                search: '검색',
                emptyTable: '데이터가 없습니다.',
                lengthMenu: '보기',
                loadingRecords: '로딩중..',
            },

            drawCallback: function () {
                var $api = this.api();
                var pages = $api.page.info().pages;
                if (pages === 0) {
                    $(".paginate_button").hide();
                }
            }

        };

        $.extend(option, customOption);

        return this.$table.DataTable(option);
    }

     setRequestData =(searchParameters, d) => {
        let requestData = d;

        if (!$.isEmptyObject(searchParameters)) {
            if (typeof searchParameters === 'string') {
                const serializedParams = decodeURIComponent(searchParameters);
                const paramsArray = serializedParams.split('&');
                paramsArray.forEach(param => {
                    const keyValue = param.split('=');
                    requestData[keyValue[0]] = keyValue[1];
                });
            } else if (Array.isArray(searchParameters)) {
                searchParameters.forEach(param => {
                    requestData[param.name] = param.value;
                });
            } else {
                requestData = $.extend({}, d, searchParameters);
            }
        }
        return requestData;
    }

    reload = () => {
        this.$table.DataTable().ajax.reload(null, false);
        this.$contentPanel.html('');
    }

    loadContent = (url) => {
        const _this = this;
        $.ajax({
            type: 'get',
            url: url,
            async: false,
            dataType: 'html'
        }).done(function (response) {
            _this.$contentPanel.html(response);
        }).fail(function (error) {
            alert('페이지를 불러오는데 실패하였습니다.');
            console.log(JSON.stringify(error));
        });

        _this.$contentPanel[0].scrollIntoView({block: 'center'})
    }

    hideContent = () => {
        this.$contentPanel.html('');
    }
}
