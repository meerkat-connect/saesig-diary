class DataTablesHelper {
    tableId = null;
    contentPanelId = null;

    constructor(tableId, contentPanelId) {
        this.tableId = tableId;
        this.contentPanelId = contentPanelId;
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
                    return res.data;
                }
            },

            language: {
                search: '검색',
                emptyTable: '데이터가 없습니다.',
                lengthMenu: '보기',
                loadingRecords: '로딩중..',
            },
        };

        $.extend(option, customOption);

        var tableId = this.tableId;
        return $('#' + tableId).DataTable(option);
    }
}
