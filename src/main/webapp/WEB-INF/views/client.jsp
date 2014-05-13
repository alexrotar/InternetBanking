<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="utf-8">
    <title>Banking</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.1/css/bootstrap.min.css">
</head>
<body>

    <div class="container">
        <h1>List of transactions</h1>
            <a href="logout" class="btn btn-warning" style="float: right;">Logout</a>
        <hr />
        <div class="page"></div>
    <input type="hidden" id="username" value="<sec:authentication property="name" />" />
        
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.4.2/underscore-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min.js"></script>
    <script src="../InternetBanking/resources/theme/js/jsrender.js"></script>

    <a href="#/new" class="btn btn-primary">New Transaction</a>
    
    <div class="table-responsive">
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
    
    <script id="new-transaction-template" type="text/x-jsrender">
          <tr>
            <td>{{>destination_account_id}}</td>
            <td>{{>transactionAmount}}</td>
            <button type="submit" class="btn">Send</button>
          </tr>
    </script>

<!--            <div class="table-responsive">
        <table class="table-account-details"><thead class="header"><tr><th>Change status</th><th>Account number</th><th>Owner name</th><th>Account Amount</th></tr></thead>
            <tbody id="accountDetails"></tbody>
        </table>
    </div>-->
<!--    <table><tbody id="account-details-form"></tbody></table>
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
            <td>
                <button type="submit" class="btn">Update</button>
            </td>
          </tr>
    </script>-->
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
                      var username = $('#username').val();
                      var account = (function () {
                            var json = null;
                            $.ajax({
                                'async': false,
                                'global': false,
                                'url': '/account/username/' + username,
                                'dataType': "json",
                                'success': function (data) {
                                    json = data;
                                }
                            });
                            return json;
                        })();
                      var accountId = account.account_id;
                      $.getJSON('/transaction/' + accountId, function(data) {
                          var transactions = data;
                          $("#transactionList").html($("#transactions-list-template").render(transactions));
                      }); 
                  }
              });
          }
        });
        
        var Router = Backbone.Router.extend({
        routes: {
          "": "home",
          "new": "home"
          }
        });

          var transactionList = new TransactionList();
//          
//          var accountDetails = new AccountDetails();

          var router = new Router;
          router.on('route:home', function() {
             transactionList.render();
          });
          router.on('route:accountDetails', function(account_id) {
             accountDetails.render({id: account_id});
          });
          Backbone.history.start();
    </script>
</body>
</html>
