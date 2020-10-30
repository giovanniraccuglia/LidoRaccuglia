<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="com.raccuglia.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <title>Marrakech Beach - Area Bagnino Page</title>
      <link rel="shortcut icon" href="img/iconaLido.ico">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script src="js/Bagnino/areaBagnino.js"></script>
      <style>
         .jumbotron {
         background-color: #5e7a8c;
         }
         h1, h2, h3, h4, h5, h6, p, label {
         font-family: -webkit-pictograph;
         }
         h2 {
         font-size: 2.3rem;
         }
         p {
         font-size: 1.3rem;
         }
         label {
         font-style: italic;
         font-size: 1.5rem;
         }
         nav {
         font-size: 1.2rem;
         }
         body, .modal-content {
         background-color: whitesmoke;
         }
         input, select {
         font-style: normal;
         }
         .my-custom-scrollbar {
         position: relative;
         height: 300px;
         overflow: auto;
         }
         .table-wrapper-scroll-y {
         display: block;
         }
         .grid-container {
         overflow-x: scroll;
         display: grid;
         grid-gap: 1px;
         background-color: #F2D16B;
         }
         .grid-container > div {
         text-align: center;
         }
         .postazione {
         margin: 2px;
         }
         .right{
         float: right;
         width: 45%;
         text-align: left;
         }
         .left{
         float: left;
         width: 55%;
         text-align: left;
         }
      </style>
   </head>
   <body>
      <!-- header e navbar -->
      <%@ include file="/WEB-INF/generale/header.jsp"%>
      <!-- body -->
      <div class="container" style="margin-top:30px; font-style: italic">
         <div class="row">
            <!-- Info Account-->
            <%@ include file="/WEB-INF/generale/infoAccount.jsp"%>
            <!-- Inserire Funzionalità -->
            <div class="col-sm-8">
               <div class="text-center">
                  <h2 class="w3-opacity">Vista Spiaggia</h2>
               </div>
               <br>
               <div id="divSpiaggia">
                  <div style="display: flex">
                     <!-- Legenda -->
                     <div class="left">
                        <h4>Legenda</h4>
                        <i class="fa fa-square" aria-hidden="true" style="color: #F2D16B; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;"></i><small class="w3-opacity"> Libero</small><br>
                        <i class="fa fa-square" aria-hidden="true" style="color: lightcoral; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;"></i><small class="w3-opacity"> Prenotato</small><br>
                        <i class="fa fa-square" aria-hidden="true" style="color: lightgray; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;"></i><small class="w3-opacity"> Non Prenotabile</small><br>
                        <br><small class="w3-opacity">NB. Ogni singola postazione è comprensiva di 2 sdraio e 1 ombrellone.</small>
                     </div>
                     <div class="right" style="font-style: normal; padding-left: 10%">
                        <!-- Date picker -->
                        <div class="form-group">
                           <label for="date" class="col-form-label" style="font-size: 1.4rem">Data</label>
                           <div>
                              <input class="form-control" type="date" id="date" name="date">
                           </div>
                        </div>
                     </div>
                  </div>
                  <br>
                  <div id="mappaLido" class="grid-container">
                     <!-- Grid Spiaggia -->
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- footer -->
      <%@ include file="/WEB-INF/generale/footer.jsp"%>
   </body>
</html>