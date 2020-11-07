<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="com.raccuglia.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <script src="js/generale/infoAccount.js"></script>
   </head>
   <body>
      <!-- Info Account -->
      <div class="col-sm-4" style="padding-right: 5%">
         <br>
         <div class="text-center">
            <img src="img/user.png" alt="user"/>
         </div>
         <div id="divInfoAccount" style="font-style: normal"></div>
         <form id="formInfoAccount" method="post">
            <div class="form-group">
               <label class="w3-opacity" for="nome">Nome:</label>
               <input type="text" class="form-control" id="nomeInfoAccount" value="${sessionScope.utente.getNome()}" name="nome" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
            </div>
            <div class="form-group">
               <label class="w3-opacity" for="cognome">Cognome:</label>
               <input type="text" class="form-control" id="cognomeInfoAccount" value="${sessionScope.utente.getCognome()}" name="cognome" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
            </div>
            <div class="form-group">
               <label class="w3-opacity" for="cellulare">Cellulare:</label>
               <input type="text" class="form-control" id="cellulareInfoAccount" value="${sessionScope.utente.getCellulare()}" name="cellulare" title="Inserire solamente caratteri numerici" pattern="[0-9]{10}" maxlength="10" required>
            </div>
            <div class="form-group">
               <label class="w3-opacity" for="email">E-mail:</label>
               <input type="email" class="form-control" id="emailInfoAccount" value="${sessionScope.utente.getEmail()}" name="email" maxlength="100" required>
            </div>
            <div>
               <button type="submit" class="btn bg-info" value="modificaInfoAccount" style="font-style: normal; color: whitesmoke"><i class="fa fa-check"></i> Salva</button>
               <a href="" class="btn bg-info" data-toggle="modal" data-target="#modalModificaPassword" style="font-style: normal; color: whitesmoke; float: right"><i class="fa fa-lock"></i> Modifica Password</a>
            </div>
         </form>
         <hr class="d-sm-none">
      </div>
      <!-- Modal Form Change Password -->
      <div class="modal fade" id="modalModificaPassword">
         <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
               <!-- Modal body -->
               <div class="modal-body">
                  <div id="divAlertModificaPassword" style="font-style: normal"></div>
                  <form id="formModificaPassword" method="post">
                     <div class="form-group">
                        <label class="w3-opacity" for="vecchiaPassword">Vecchia Password:</label>
                        <input type="password" class="form-control" id="vecchiaPassword" placeholder="Inserire password" name="vecchiaPassword" title="Deve contenere almeno un numero e una lettera minuscola e maiuscola, e deve contenere almeno 8 caratteri" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="45" required>
                     </div>
                     <div class="form-group">
                        <label class="w3-opacity" for="nuovaPassword">Nuova Password:</label>
                        <input type="password" class="form-control" id="nuovaPassword" placeholder="Inserire password" name="nuovaPassword" title="Deve contenere almeno un numero e una lettera minuscola e maiuscola, e deve contenere almeno 8 caratteri" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="45" required>
                     </div>
                     <div class="form-group">
                        <label class="w3-opacity" for="confermaPassword">Conferma Password:</label>
                        <input type="password" class="form-control" id="confermaPassword" placeholder="Inserire password" name="confermaPassword" title="Deve contenere almeno un numero e una lettera minuscola e maiuscola, e deve contenere almeno 8 caratteri" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" maxlength="45" required>
                     </div>
                     <div class="modal-footer">
                        <button id="closeModificaPassword" type="button" class="btn btn-secondary" data-dismiss="modal" style="font-style: normal"><i class="fa fa-close"></i> close</button>
                        <button type="submit" class="btn bg-info active" value="modificaPassword" style="font-style: normal; color: white"><i class="fa fa-unlock-alt"></i> modifica</button>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>