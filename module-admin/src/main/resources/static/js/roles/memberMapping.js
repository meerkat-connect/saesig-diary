"use strict";

let mappedUserTable = null;

const memberMappingController =  {
    findMappedMembers : function(roleId) {
        if(!!mappedUserTable) mappedUserTable.destroy();

        mappedUserTable = $('#mappedUserTable').DataTable({
            "columnDefs": [
                {className: "dt-center", targets: "_all"}
            ],

            pagingType: 'full_numbers',
            lengthChange: false,
            responsive: true,
            searching: false,
            ordering: false,
            paging: true,
            info: true,
            pageLength: 10,
            bPaginate: true,
            bAutoWidth: true,
            serverSide: true,
            processing: true,

            ajax: {
                async: false,
                url: `/admin/roles/${roleId}/members/`,
                method: 'get',
                "dataSrc": function (res) {
                    return res.data;
                }
            },

            columns: [
                {data: 'id'},
                {data: 'email'},
                {data: 'nickname'}
            ],

            language: {
                search : '검색',
                emptyTable: '데이터가 없습니다.',
                info: '_START_부터 _END_까지 / 총데이터 : _TOTAL_',
                infoEmpty : '데이터 0 부터 0 까지 0 총데이터',
                lengthMenu : '보기',
                loadingRecords : '로딩중..',
            },

            rowCallback : function(row, data, dataIndex) {
                // row id 얻기
                var rowId = data.id;
                /*if ($.inArray(rowId, rows_selected) !== -1) { // row 선택됐을때 실행할(rowId가 rows_selected 배열에 있으면)
                    $(row).find('input[type="checkbox"]').prop(
                        'checked', true);
                    $(row).addClass('selected');
                }*/
            }
        });

        $('#mappedUserTable').show();
    }
};
