<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="com.raccuglia.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <title>Marrakech Beach - Area Admin Page</title>
      <link rel="shortcut icon" href="img/iconaLido.ico">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script src="js/Amministratore/areaAdmin.js"></script>
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
            <!-- Liste -->
            <div class="col-sm-8" style="padding-left: 3%">
               <!-- Lista Dipendenti -->
               <div>
                  <br>
                  <!-- Modal Form Nuovo Dipendente -->
                  <a href="" class="btn bg-info" data-toggle="modal" data-target="#modalNuovoDipendente" style="font-style: normal; color: whitesmoke"><i class="fa fa-plus"></i> Dipendente</a>
                  <!-- The Modal -->
                  <div class="modal fade" id="modalNuovoDipendente">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div class="modal-body">
                              <div id="divAlertNuovoDipendente" style="font-style: normal"></div>
                              <form id="formNuovoDipendente" method="post">
                                 <div class="form-group">
                                    <label class="w3-opacity" for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nomeNuovoDipendente" placeholder="Inserire nome" name="nome" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="cognome">Cognome:</label>
                                    <input type="text" class="form-control" id="cognomeNuovoDipendente" placeholder="Inserire cognome" name="cognome" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="cellulare">Cellulare:</label>
                                    <input type="text" class="form-control" id="cellulareNuovoDipendente" placeholder="Inserire cellulare" name="cellulare" title="Inserire solamente caratteri numerici" pattern="[0-9]{10}" maxlength="10" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="email">E-mail:</label>
                                    <input type="email" class="form-control" id="emailNuovoDipendente" placeholder="Inserire email" name="email" maxlength="100" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="ruolo">Ruolo:</label>
                                    <select class="form-control" id="ruoloNuovoDipendente" name="ruolo">
                                       <option>Bagnino</option>
                                       <option>Ristorazione</option>
                                       <option>Cassiere</option>
                                    </select>
                                 </div>
                                 <div class="modal-footer">
                                    <button id="closeNuovoDipendente" type="button" class="btn btn-secondary" data-dismiss="modal" style="font-style: normal"><i class="fa fa-close"></i> close</button>
                                    <button type="submit" class="btn bg-info active" value="nuovoDipendente" style="font-style: normal; color: white"><i class="fa fa-user"></i> registra</button>
                                 </div>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div style="font-style: normal">
                     <br>
                     <div id="divAlertDipendente" style="font-style: normal"></div>
                     <div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
                        <table class="table table-hover">
                           <thead class="table-info">
                              <tr>
                                 <th>Nome</th>
                                 <th>Cognome</th>
                                 <th>Cellulare</th>
                                 <th>E-mail</th>
                                 <th>Ruolo</th>
                                 <th></th>
                              </tr>
                           </thead>
                           <tbody id="tableDipendenti">
                              <!-- Record Dipendenti -->
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
               <!-- Lista Prodotti -->
               <div>
                  <br>
                  <!-- Modal Form Nuovo Dipendente -->
                  <a href="" class="btn bg-info" data-toggle="modal" data-target="#modalNuovoProdotto" style="font-style: normal; color: whitesmoke"><i class="fa fa-plus"></i> Prodotto</a>
                  <!-- The Modal -->
                  <div class="modal fade" id="modalNuovoProdotto">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div class="modal-body">
                              <div id="divAlertNuovoProdotto" style="font-style: normal"></div>
                              <form id="formNuovoProdotto" method="post">
                                 <div class="form-group">
                                    <label class="w3-opacity" for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nomeNuovoProdotto" placeholder="Inserire nome" name="nome" maxlength="100" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="descrizione">Descrizione:</label>
                                    <input type="text" class="form-control" id="descrizioneNuovoProdotto" placeholder="Inserire descrizione" name="descrizione" maxlength="300" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="prezzo">Prezzo:</label>
                                    <input type="number" class="form-control" id="prezzoNuovoProdotto" placeholder="Inserire prezzo" name="prezzo" step="0.01" min="0" max="9999" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="categoria">Categoria:</label>
                                    <select class="form-control" id="categoriaNuovoProdotto" name="categoria">
                                       <option>Colazione Bar</option>
                                       <option>Panini Bar</option>
                                       <option>Rustici Bar</option>
                                       <option>Insalatone Bar</option>
                                       <option>Frutta e Dessert Bar</option>
                                       <option>Antipasti Di Mare</option>
                                       <option>Antipasti Di Terra</option>
                                       <option>Primi Di Mare</option>
                                       <option>Primi Di Terra</option>
                                       <option>Secondi Di Mare</option>
                                       <option>Secondi Di Terra</option>
                                       <option>Dessert</option>
                                       <option>Caffetteria</option>
                                       <option>Birre</option>
                                       <option>Cocktails</option>
                                       <option>Vini Bianchi</option>
                                       <option>Vini Rossi</option>
                                       <option>Distillati</option>
                                       <option>Analcolici</option>
                                    </select>
                                 </div>
                                 <div class="modal-footer">
                                    <button id="closeNuovoProdotto" type="button" class="btn btn-secondary" data-dismiss="modal" style="font-style: normal"><i class="fa fa-close"></i> close</button>
                                    <button type="submit" class="btn bg-info active" value="nuovoProdotto" style="font-style: normal; color: white"><i class="fa fa-cutlery"></i> inserisci</button>
                                 </div>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div style="font-style: normal">
                     <br>
                     <div id="divAlertProdotto" style="font-style: normal"></div>
                     <div class="table-responsive table-wrapper-scroll-y my-custom-scrollbar">
                        <table class="table table-hover">
                           <thead class="table-info">
                              <tr>
                                 <th>Nome e Descrizione</th>
                                 <th>Prezzo</th>
                                 <th>Categoria</th>
                                 <th></th>
                              </tr>
                           </thead>
                           <tbody id="tableProdotti">
                              <!-- Record Prodotti -->
                           </tbody>
                        </table>
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