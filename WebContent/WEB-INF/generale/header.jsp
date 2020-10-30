<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="com.raccuglia.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
   <head></head>
   <body>
      <!-- header -->
      <div class="jumbotron text-center" style="margin-bottom:0">
         <h1 style="font-size: 3rem; color: gainsboro">MARRAKECH BEACH</h1>
      </div>
      <!-- navbar -->
      <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
         <a class="navbar-brand" href="${pageContext.request.contextPath}/"><i class="fa fa-home"></i> Home</a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
         <span class="navbar-toggler-icon"></span>
         </button>
         <% if(request.getSession().getAttribute("utente") == null) { %>
         <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
               <li class="nav-item">
                  <!-- Modal Form Login -->
                  <a class="nav-link" href="" data-toggle="modal" data-target="#modalLogin">Accedi</a>
                  <!-- The Modal -->
                  <div class="modal fade" id="modalLogin">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div class="modal-body">
                              <div id="divAlertLogin"></div>
                              <form id="formLogin" method="post">
                                 <div class="form-group">
                                    <label class="w3-opacity" for="email">E-mail:</label>
                                    <input type="email" class="form-control" id="emailLogin" placeholder="Inserire email" name="email" maxlength="100" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="password">Password:</label>
                                    <input type="password" class="form-control" id="passwordLogin" placeholder="Inserire password" name="password" title="Deve contenere almeno un numero e una lettera minuscola e maiuscola, e deve contenere almeno 8 caratteri" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,45}" maxlength="45" required>
                                 </div>
                                 <a id="linkResetPassword" href="" data-toggle="modal" data-target="#modalResetPassword" style="color: blue; font-size: 1rem">Password dimenticata ?</a>
                                 <div class="modal-footer">
                                    <button id="closeLogin" type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-close"></i> close</button>
                                    <button type="submit" class="btn bg-info active" value="login" style="color: white"><i class="fa fa-sign-in"></i> accedi</button>
                                 </div>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- Modal Reset Password -->
                  <div class="modal fade" id="modalResetPassword">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div class="modal-body">
                              <div id="divAlertResetPassword"></div>
                              <form id="formResetPassword" method="post">
                                 <div class="form-group">
                                    <label class="w3-opacity" for="email">E-mail:</label>
                                    <input type="email" class="form-control" id="emailResetPassword" placeholder="Inserire email" name="email" maxlength="100" required>
                                 </div>
                                 <div class="modal-footer">
                                    <button id="closeResetPassword" type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-close"></i> close</button>
                                    <button type="submit" class="btn bg-info active" value="resetPassword" style="color: white"><i class="fa fa-unlock-alt"></i> reset</button>
                                 </div>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
               </li>
               <li class="nav-item">
                  <!-- Modal Form Registrazione -->
                  <a class="nav-link" href="" data-toggle="modal" data-target="#modalRegistrazione">Registrati</a>
                  <!-- The Modal -->
                  <div class="modal fade" id="modalRegistrazione">
                     <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                           <!-- Modal body -->
                           <div class="modal-body">
                              <div id="divAlertRegistrazione"></div>
                              <form id="formRegistrazione" method="post">
                                 <div class="form-group">
                                    <label class="w3-opacity" for="nome">Nome:</label>
                                    <input type="text" class="form-control" id="nomeRegistrazione" placeholder="Inserire nome" name="nome" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="cognome">Cognome:</label>
                                    <input type="text" class="form-control" id="cognomeRegistrazione" placeholder="Inserire cognome" name="cognome" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="cellulare">Cellulare:</label>
                                    <input type="text" class="form-control" id="cellulareRegistrazione" placeholder="Inserire cellulare" name="cellulare" title="Inserire solamente caratteri numerici" pattern="[0-9]{10}" maxlength="10" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="email">E-mail:</label>
                                    <input type="email" class="form-control" id="emailRegistrazione" placeholder="Inserire email" name="email" maxlength="100" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="password">Password:</label>
                                    <input type="password" class="form-control" id="passwordRegistrazione" placeholder="Inserire password" name="password" title="Deve contenere almeno un numero e una lettera minuscola e maiuscola, e deve contenere almeno 8 caratteri" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="45" required>
                                 </div>
                                 <div class="form-group">
                                    <label class="w3-opacity" for="ripassword">Conferma Password:</label>
                                    <input type="password" class="form-control" id="ripasswordRegistrazione" placeholder="Inserire password" name="ripassword" title="Deve contenere almeno un numero e una lettera minuscola e maiuscola, e deve contenere almeno 8 caratteri" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="45" required>
                                 </div>
                                 <div class="form-group form-check">
                                    <label class="w3-opacity" class="form-check-label"></label>
                                    <input class="form-check-input" type="checkbox" name="remember" required> Accetto Termini e Condizioni.
                                 </div>
                                 <div class="modal-footer">
                                    <button id="closeRegistrazione" type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-close"></i> close</button>
                                    <button type="submit" class="btn bg-info active" value="registrazione" style="color: white"><i class="fa fa-user"></i> registrati</button>
                                 </div>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
               </li>
            </ul>
         </div>
         <% }else if(((Utente) request.getSession().getAttribute("utente")).getRuolo().equals("Cliente")) { %>
         <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
               <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/areaUtente">Area Personale</a>
               </li>
               <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/carrello">Carrello()</a>
               </li>
            </ul>
         </div>
         <div class="collapse navbar-collapse" id="collapsibleNavbar" style="place-content: flex-end">
            <ul class="navbar-nav">
               <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/logout">Disconnetti</a>
               </li>
            </ul>
         </div>
         <% }else { %>
         <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
               <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/areaUtente">Area Personale</a>
               </li>
            </ul>
         </div>
         <div class="collapse navbar-collapse" id="collapsibleNavbar" style="place-content: flex-end">
            <ul class="navbar-nav">
               <li class="nav-item">
                  <a class="nav-link" href="${pageContext.request.contextPath}/logout">Disconnetti</a>
               </li>
            </ul>
         </div>
         <%} %>
      </nav>
   </body>
</html>