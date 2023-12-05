class DataTablesHelper {
    tableId = null;
    contentPanelId = null;
    $table = null;
    $contentPanel = null;

    constructor(tableId, contentPanelId) {
        this.tableId = tableId;
        this.contentPanelId = contentPanelId;
        this.$table = $('#'+ tableId);
        this.$contentPanel = $('#' + contentPanelId);
    }

    datatables = (url, customOption) =>  {
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
                url: url,
                method: 'get',
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

    reload = () => {
        this.$table.DataTable().ajax.reload(null,false);
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
        });

        _this.$contentPanel[0].scrollIntoView({block: 'center'})
    }

    hideContent = () => {
        this.$contentPanel.html('');
    }
}
