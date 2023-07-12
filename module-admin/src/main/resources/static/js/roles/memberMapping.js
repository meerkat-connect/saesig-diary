"use strict";

let mappedUserTable = null;

const memberMappingController =  {
    findMappedMembers : function(roleId) {
        if(!!mappedUserTable) mappedUserTable.destroy();

        mappedUserTable = $('#mappedUserTable').DataTable({
            /*"columnDefs": [
                {orderable: false, className: 'select-checkbox', targets: 0},
                {className: "dt-center", targets: "_all"}
            ],
            select: {
                style: 'os',
                selector: 'td:first-child'
            },*/
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
                {data: 'nickname'},
            ]
        });

        $('#mappedUserTable').show();
        /*$.ajax( {
            async:false,
            url: `/admin/roles/${roleId}/members/`,
            method: 'get',
            dataType: 'application/json',
        }).done(function(response){
            console.log(response);
        }).fail(function(error) {
            alert('매핑된 사용자 목록을 조회하는데 실패하였습니다.');
            console.log(JSON.stringify(error));
        });*/
    }
};
