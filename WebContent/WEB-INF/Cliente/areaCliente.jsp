<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="com.raccuglia.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <title>Marrakech Beach - Area Cliente Page</title>
      <link rel="shortcut icon" href="img/iconaLido.ico">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script src="js/Cliente/areaCliente.js"></script>
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
            <!-- Funzionalità -->
            <div class="col-sm-8" style="padding-left: 3%">
               <!-- Storico Prenotazioni -->
               <div>
                  <br><br>
                  <!-- Modal Form Nuova Prenotazione -->
                  <a id="linkNuovaPrenotazione" href="" class="btn bg-info" data-toggle="modal" data-target="#modalNuovaPrenotazione" style="font-style: normal; color: whitesmoke"><i class="fa fa-plus"></i> Prenota</a>
                  <!-- Modal Prenotazione -->
                  <%@ include file="/WEB-INF/Cliente/nuovaPrenotazione.jsp"%>
                  <!-- Modal Pagamento -->
                  <%@ include file="/WEB-INF/Cliente/pagamento.jsp"%>
                  <!-- Tabella Prenotazioni -->
                  <div style="font-style: normal">
                     <br>
                     <div id="divAlertPrenotazione" style="font-style: normal"></div>
                     <div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
                        <table class="table table-hover">
                           <thead class="table-info">
                              <tr>
                                 <th>Data</th>
                                 <th>Status</th>
                                 <th></th>
                                 <th></th>
                              </tr>
                           </thead>
                           <tbody id="tablePrenotazioni">
                              <!-- Record Prenotazioni -->
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <!-- The Modal Visualizza Prenotazione -->
                  <div class="modal fade" id="modalVisualizzaPrenotazione">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div id="modalBodyVisualizzaPrenotazione" class="modal-body w3-opacity">
                              <!-- Info Prenotazione -->
                           </div>
                           <div class="modal-footer">
                              <button id="closeVisualizzaPrenotazione" type="button" class="btn btn-secondary" data-dismiss="modal" style="font-style: normal"><i class="fa fa-close"></i> close</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <!-- Storico Ordini -->
               <div>
                  <br>
                  <!-- Modal Form Nuovo Ordine -->
                  <a id="linkNuovoOrdine" href="" class="btn bg-info" data-toggle="modal" data-target="#modalNuovoOrdine" style="font-style: normal; color: whitesmoke"><i class="fa fa-plus"></i> Ordina</a>
                  <!-- Modal Ordine -->
                  <%@ include file="/WEB-INF/Cliente/nuovoOrdine.jsp"%>
                  <!-- Tabella Ordini -->
                  <div style="font-style: normal">
                     <br>
                     <div id="divAlertOrdine" style="font-style: normal"></div>
                     <div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
                        <table class="table table-hover">
                           <thead class="table-info">
                              <tr>
                                 <th>Data</th>
                                 <th>Status</th>
                                 <th></th>
                              </tr>
                           </thead>
                           <tbody id="tableOrdini">
                              <!-- Record Ordini -->
                           </tbody>
                        </table>
                     </div>
                  </div>
                  <!-- The Modal Visualizza Ordine -->
                  <div class="modal fade" id="modalVisualizzaOrdine">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div id="modalBodyVisualizzaOrdine" class="modal-body w3-opacity">
                              <!-- Info Ordine -->
                           </div>
                           <div class="modal-footer">
                              <button id="closeVisualizzaOrdine" type="button" class="btn btn-secondary" data-dismiss="modal" style="font-style: normal"><i class="fa fa-close"></i> close</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- footer -->
      <%@ include file="/WEB-INF/generale/footer.jsp"%>
   </body>
</html>