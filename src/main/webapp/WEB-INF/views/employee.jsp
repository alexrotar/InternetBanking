<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="utf-8">
    <title>Banking</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../InternetBanking/resources/theme/js/backgrid.css" />
    <link rel="stylesheet" href="../InternetBanking/resources/theme/js/backgrid-paginator.css" />
</head>
<body>
    
    <div class="container">
        <h1>Bank accounts</h1>
            <a href="logout" class="btn btn-warning" style="float: right;">Logout</a>
        <hr />
        <div class="page"></div>
     
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.2/underscore-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.0/backbone-min.js"></script>
    <script src="../InternetBanking/resources/theme/js/backgrid.js"></script>
    <script src="../InternetBanking/resources/theme/js/backgrid-paginator.js"></script>
    <script src="../InternetBanking/resources/theme/js/backbone.paginator.js"></script>
    <script src="../InternetBanking/resources/theme/js/jsrender.js"></script>

    <div class="table-responsive template accountList">
            <table class="table "><thead class="header"><tr><th>Account status</th><th>Account number</th><th>Owner name</th><th>Account details</th></tr></thead>
                <tbody id="accountList"></tbody>
    </table>
<!--        <div id="grid" class="table-responsive"></div>
        <div id="paginator" class="table-responsive"></div>-->
    </div>
    
    
    
    <script id="accounts-list-template" type="text/x-jsrender">
          <tr>
            <td>{{>status}}</td>
            <td>{{>account_id}}</td>
            <td>{{>ownerName}}</td>
            <td><a class="btn" href="#/edit/{{>account_id}}">Details</a></td>
          </tr>
    </script>

<div class="table-responsive template account-details-form">
    <table class="table" ><thead class="header"><tr><th>Change status</th><th>Account number</th><th>Owner name</th><th>Account Amount</th></tr></thead>
        <tbody id="account-details-form" ></tbody></table>
    </div>
    <script id="account-details-template" type="text/x-jsrender">
          <tr>
            <td>
                 <select class="selectpicker" data-style="btn-warning" id="accountStatus">
                    <option>{{>status}}</option>
                        {{if status == "NEW"}}
				<option>ACTIVE</option>
			{{else status == "ACTIVE"}}
				<option>BLOCKED</option>
			{{else status == "BLOCKED"}}
				<option>ACTIVE</option>
			{{/if}}
                </select>
            </td>
            <td>{{>account_id}}</td>
            <td>{{>ownerName}}</td>
            <td>{{>accountAmount}}</td>
          </tr>
                <input id="accountId" type="hidden" name="account_id" value="{{>account_id}}" />
    </script>
    
    <div class="table-responsive template transactions-list">
            <table class="table"><thead class="header"><tr><th>Source account</th><th>Destination account</th><th>Date/time of the transaction</th><th>Amount of money transferred</th></tr></thead>
                <tbody id="transactionList"></tbody>
    </table>
    </div>
    
    <script id="transactions-list-template" type="text/x-jsrender">
          <tr>
            <td>{{>account_id}}</td>
            <td>{{>destination_account_id}}</td>
            <td>{{>dateTime}}</td>
            <td>{{>transactionAmount}}</td>
          </tr>
    </script>
   </div>
    <script>
        
        $.ajaxPrefilter( function(options, originalOptions, jqXHR) {
            options.url = 'http://localhost:8084/InternetBanking/info' + options.url;
        });
        
        var Transaction = Backbone.Model.extend({
          idAttribute : 'transactionId',
          urlRoot: '/transaction/'
        });
        
        var Transactions = Backbone.Collection.extend({
          model: Transaction,
          url: '/transaction'
        });

        var TransactionList = Backbone.View.extend({
          el: '.page',
          render: function(){
              var transactions = new Transactions();
              transactions.fetch({
                  success: function(){
                      $.getJSON('/transaction', function(data) {
                          var transactions = data;
                          $("#transactionList").html($("#transactions-list-template").render(transactions));
                      }); 
                  }
              });
          }
        });
        
        var Account = Backbone.Model.extend({
          idAttribute : 'account_id',
          urlRoot: '/account/'
        });
        
        var Accounts =  Backbone.Collection.extend({
          model: Account,
          url: '/account'
//          state: {
//            firstPage: 0,
//            currentPage: 0,
//            pageSize: 10
//          },
//            queryParams: {
//              currentPage: "current_page",
//              pageSize: "page_size"
//            }
        });
        
        
        
        var AccountDetails = Backbone.View.extend({
          el: 'body',
          events: {
              'change #accountStatus': 'updateAccount'
          },
          render: function(options){
              $.getJSON('/account/' + options.id, function(data) {
                          var accountss = data;
                          $("#account-details-form").html($("#account-details-template").render(accountss));
             
          }),
                  $.getJSON('/transaction/' + options.id, function(data) {
                          var transactions = data;
                          $("#transactionList").html($("#transactions-list-template").render(transactions));
                      }); 
        },
        updateAccount: function(){
              var accountDetails = $('#accountStatus option:selected').val();
              var id = $('#accountId').val();
              var account = new Account({account_id: id});
              account.save( {status: accountDetails}, {
                  success: function(){
                      router.navigate('', {trigger: true});
                  }
              });
              return false;
          }
    });
        

        var AccountList = Backbone.View.extend({
          el: '.page',
          render: function(){
              var accounts = new Accounts([], { mode: "client" });
              accounts.fetch({
                  success: function(){
                      $.getJSON('/account', function(data) {
                          var json = data;
                          $("#accountList").html($("#accounts-list-template").render(json));
                      }); 
//      var columns = [{
//        name: "account_id",
//        editable: false,
//        cell: Backgrid.IntegerCell.extend({
//          orderSeparator: ''
//        })
//      }, {
//        name: "status",
//        cell: "string"
//      }, {
//        name: "accountAmount",
//        cell: "number"
//      }, {
//        name: "ownerName",
//        cell: "string"
//      }];
//
//      var Territories = Backbone.PageableCollection.extend({
//        url: '/account',
//        mode: "client",
//        state: {
//                pageSize: 10
//          }
//      });
//      
//      var territories = new Territories();
//
//      var grid = new Backgrid.Grid({
//        columns: columns,
//        collection: territories
//      });       
//      
//      var paginator = new Backgrid.Extension.Paginator({
//        collection: territories
//      });
//
//      $("#grid").append(grid.render().$el);
//      $("#paginator").append(paginator.render().$el);
//
//      territories.fetch();
                  }
              });
          }
        });
        
        var Router = Backbone.Router.extend({
        routes: {
          "": "home",
          "edit/:id": "accountDetails",
          "account/page/:page"	: "home"
          }
        });

          var accountList = new AccountList();
          
          var accountDetails = new AccountDetails();

          var router = new Router;
          router.on('route:home', function() {
              $("div.template").hide();
              $(".accountList").show();
             accountList.render();
          });
          router.on('route:accountDetails', function(account_id) {
              $("div.template").hide();
              $(".account-details-form").show();
              $(".transactions-list").show();
             accountDetails.render({id: account_id});
          });
          Backbone.history.start();
    </script>
</body>
</html>