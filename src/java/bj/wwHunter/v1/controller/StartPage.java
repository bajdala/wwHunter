package bj.wwHunter.v1.controller;

import bj.wwHunter.v1.model.EventState;
import bj.wwHunter.v1.model.Gameevents;
import bj.wwHunter.v1.model.GamerState;
import bj.wwHunter.v1.model.Gamers;
import bj.wwHunter.v1.model.MessageSenderState;
import bj.wwHunter.v1.model.MessageState;
import bj.wwHunter.v1.model.Messages;
import bj.wwHunter.v1.model.Model;
import bj.wwHunter.v1.model.ModelImpl;
import bj.wwHunter.v1.model.NewState;
import bj.wwHunter.v1.model.News;
import bj.wwHunter.v1.model.PenaltyState;
import bj.wwHunter.v1.model.Penaltys;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Сервлет-контроллер
 * @author bajdala
 */
public class StartPage extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        request.setCharacterEncoding("cp1251");
        Model model = new ModelImpl();

        //используется для генерации имени файла фотографии
        Random rand = new Random();

        //устанавливается значение operation
        String operation = request.getParameter("operation");
        if (operation == null) {
            operation = "index";
        }
        
        //устанавливается дефолтное значение url
        String url = "index.jsp";
        

        //обработка значений operation
        if (operation.equals("admin")) {                        //интерфейс администратора

            if (request.getParameter("adminPassword") != null && request.getParameter("adminPassword").equals("Gh@dbk0")) {

                List<Gamers> gamers = model.getGamersDAO().selectAll();
                List<News> news = model.getNewsDAO().selectAll();
                List<Penaltys> pnlts = model.getPenaltysDAO().selectAll();
                Map<Penaltys, Gamers> penaltys = new LinkedHashMap<Penaltys, Gamers>();
                for (Penaltys p : pnlts) {
                    Gamers g = model.getGamersDAO().selectByID(p.getIdGamer());
                    penaltys.put(p, g);
                }

                List<Gameevents> gmevents = model.getGameEventsDAO().selectAll();
                Map<Gameevents, Gamers[]> gameEvents = new LinkedHashMap<Gameevents, Gamers[]>();
                for (Gameevents ge : gmevents) {
                    Gamers hunter = model.getGamersDAO().selectByID(ge.getIdHunter());
                    Gamers victim = model.getGamersDAO().selectByID(ge.getIdVictim());
                    Gamers[] seed = new Gamers[2];
                    seed[0] = hunter;
                    seed[1] = victim;                    
                    gameEvents.put(ge, seed);
                }

                Map<String, String> map = new HashMap<String, String>();
                map.put("COMPLETE", "успешная охота");
                map.put("DEFENSE", "активация неуязвимости жертвой");
                map.put("DISQUALIFICATION", "дисквалификация охотника");
                map.put("NEWVICTIM", "новая жертва");
                map.put("PENALTY", "жертве назначена явка");

                request.setAttribute("gamers", gamers);
                request.setAttribute("news", news);
                request.setAttribute("penaltys", penaltys);
                request.setAttribute("gameEvents", gameEvents);
                request.setAttribute("geStatus", map);

                url = "admin.jsp";
            } else {
                url = "adminIndex.jsp";
            }
            
        } else if (operation.equals("contacts")) {              //контактная информация
            
            url = "contacts.jsp";
            
        } else if (operation.equals("rules")) {                 //правила и описание игры
            
            url = "rules.jsp";
            
        } else if (operation.equals("privatePage")) {           //личная страница игрока
            
            url = "error.jsp";
            if (request.getParameter("userName") != null && !request.getParameter("userName").equals("")) {
                Gamers gamer = model.getGamersDAO().getGamer(request.getParameter("userName"), request.getParameter("password"));
                if (gamer != null && gamer.getStts() == GamerState.ACTIVE) {
                    Gamers victim = model.getGamersDAO().selectByID(gamer.getIdVictim());
                    String activity = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(model.getGameEventsDAO().getActivity(gamer));
                    Penaltys activePenalty = model.getPenaltysDAO().selectActiveByIDGamer(gamer.getId());

                    String immunitet = "не активирована";

                    Date d = gamer.getDateTimeImmunitet();
                    Calendar date = Calendar.getInstance();
                    date.setTime(d);
                    date.add(Calendar.DAY_OF_MONTH, 1);
                    if (new Date().before(date.getTime())) {
                        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        immunitet = formater.format(date.getTime());
                    }

                    List<Messages> msgs = model.getMessagesDAO().selectByIDGamer(gamer.getId());
                    List<String> messages = new ArrayList<String>();
                    for (Messages msg : msgs) {
                        String item = msg.getStringDate() + " ";
                        if (msg.getIdGamer() == gamer.getId()) {
                            item = item + msg.getMessageSender() + ">: ";
                        } else if (msg.getIdSender() == gamer.getId()) {
                            item = item + ((msg.getSndr() == MessageSenderState.HUNTER) ? "Жертве" : "Охотнику") + "<: ";
                        }
                        item = item + msg.getStringTextMessage() + "\n";
                        messages.add(item);
                    }

                    String immunitetVict = "не активирована";

                    Date dv = victim.getDateTimeImmunitet();
                    Calendar datev = Calendar.getInstance();
                    datev.setTime(dv);
                    datev.add(Calendar.DAY_OF_MONTH, 1);
                    if (new Date().before(datev.getTime())) {
                        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        immunitetVict = formater.format(datev.getTime());
                    }

                    Penaltys activePenaltyVict = model.getPenaltysDAO().selectActiveByIDGamer(victim.getId());

                    if (activePenalty != null) {
                        request.setAttribute("penalty", activePenalty);
                    } else {
                        request.setAttribute("penalty", new Penaltys());
                    }
                    if (activePenaltyVict != null) {
                        request.setAttribute("penaltyVict", activePenaltyVict);
                    } else {
                        request.setAttribute("penaltyVict", new Penaltys());
                    }
                    request.setAttribute("victim", victim);
                    request.setAttribute("gamer", gamer);
                    request.setAttribute("activity", activity);
                    request.setAttribute("immunitet", immunitet);
                    request.setAttribute("victImmunitet", immunitetVict);
                    request.setAttribute("messages", messages);
                    url = "privatePage.jsp";
                }
            }
            
        } else if (operation.equals("regForm")) {               //заявка на участие в игре
            
            url = "regForm.jsp";
            
        } else if (operation.equals("userPage")) {              //интерфейс администратора/личные данные игрока
            
            int idGamer = Integer.parseInt(request.getParameter("id"));

            Gamers gamer = model.getGamersDAO().selectByID(idGamer);
            Penaltys penalty = model.getPenaltysDAO().selectActiveByIDGamer(gamer.getId());

            request.setAttribute("gamer", gamer);
            request.setAttribute("penalty", penalty);

            url = "userPage.jsp";
        } else if (operation.equals("error")) {                 //сообщение об ошибке
            
            url = "error.jsp";
            
        } else if (operation.equals("index")) {                 //главная страница
            
            List<News> news = model.getNewsDAO().selectActive();
            request.setAttribute("news", news);
            
        } else if (operation.equals("activateImmunitet")) {     //обработка активации иммунитета

            int idGamer = Integer.parseInt(request.getParameter("idGamer"));

            Gamers gamer = model.getGamersDAO().selectByID(idGamer);
            Gamers hunter = model.getGamersDAO().selectByIDVictim(gamer.getId());
            if (gamer != null && hunter != null) {
                model.getGameEventsDAO().insert(new Gameevents(new Date(), gamer.getId(), hunter.getId(), EventState.DEFENSE));
                gamer.setDateTimeImmunitet(new Date());
                Messages msg = new Messages(hunter.getId(), gamer.getId(), MessageSenderState.VICTIM, new Date(), getLatin1String("Ваша жертва активировала неуязвимость!"), MessageState.NEW);
                model.getMessagesDAO().insert(msg);
                model.getGamersDAO().update(gamer);
            }
            url = "StartPage?operation=privatePage&userName=" + gamer.getNickName() + "&password=" + gamer.getLifeCode();
            
        } else if (operation.equals("sendMessage")) {           //отправка сообщения игроком с приватной странийы
            
            int idGamer = Integer.parseInt(request.getParameter("idGamer"));
            Gamers gamer = model.getGamersDAO().selectByID(idGamer);

            if (!request.getParameter("textMessage").equals("")) {
                if (request.getParameter("sndVictim") != null) {
                    Messages msg = new Messages(gamer.getIdVictim(), gamer.getId(), MessageSenderState.HUNTER, new Date(), getLatin1String(request.getParameter("textMessage")), MessageState.NEW);
                    model.getMessagesDAO().insert(msg);
                } else if (request.getParameter("sndHunter") != null) {
                    Gamers hunt = model.getGamersDAO().selectByIDVictim(gamer.getId());
                    Messages msg = new Messages(hunt.getId(), gamer.getId(), MessageSenderState.VICTIM, new Date(), getLatin1String(request.getParameter("textMessage")), MessageState.NEW);
                    model.getMessagesDAO().insert(msg);
                }
            }

            url = "StartPage?operation=privatePage&userName=" + gamer.getNickName() + "&password=" + gamer.getLifeCode();
            
        } else if (operation.equals("killVictim")) {            //обработка успешного поражения жертвы
            
            int idGamer = Integer.parseInt(request.getParameter("idGamer"));
            Gamers gamer = model.getGamersDAO().selectByID(idGamer);
            Gamers victim = model.getGamersDAO().selectByID(gamer.getIdVictim());

            String LC = request.getParameter("LC");

            if (victim.getLifeCode().equals(LC)) {

                Gameevents cmpl = new Gameevents(new Date(), victim.getId(), gamer.getId(), EventState.COMPLETE);

                gamer.setIdVictim(victim.getIdVictim());
                Gamers newVict = model.getGamersDAO().selectByID(gamer.getIdVictim());
                newVict.setDateTimeImmunitet(new Date(100, 0, 1));
                victim.setIdVictim(0);
                victim.setStatus(GamerState.DEAD);
                model.getGamersDAO().update(newVict);
                model.getGamersDAO().update(gamer);
                model.getGamersDAO().update(victim);
                model.getGameEventsDAO().insert(cmpl);

                Messages msg = new Messages(newVict.getId(), 9999, MessageSenderState.IS, new Date(), getLatin1String("Вашего охотника замочили. Теперь Вас ищет новый охотник."), MessageState.NEW);

                model.getMessagesDAO().insert(msg);

            }

            url = "StartPage?operation=privatePage&userName=" + gamer.getNickName() + "&password=" + gamer.getLifeCode();
            
        } else if (operation.equals("startGame")) {             //обработка начала игры.(активируются аккаунты игроков, каждому игроку назначается охотник)

            LCFiller filler = new LCFiller();

            List<Gamers> gamers = filler.fillLC(model.getGamersDAO().selectAllActive());

            for (Gamers g : gamers) {
                Gameevents evnt = new Gameevents(new Date(), g.getIdVictim(), g.getId(), EventState.NEWVICTIM);
                model.getGameEventsDAO().insert(evnt);
                model.getGamersDAO().update(g);
            }

            url = "StartPage?operation=admin&adminPassword=Gh@dbk0";
            
        } else if (operation.equals("addGamer")) {              //добавление нового игрока

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);


            if (!isMultipart) {

                url = "adminIndex.jsp";

            } else {
                try {
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                    factory.setRepository(repository);
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    upload.setSizeMax(1024 * 1024 * 10);

                    List<FileItem> items = upload.parseRequest(request);

                    Gamers gamer = new Gamers();

                    for (FileItem i : items) {
                        if (i.isFormField()) {

                            if (i.getFieldName().equals("nickName")) {
                                gamer.setNickName(i.getString());
                            } else if (i.getFieldName().equals("lastName")) {
                                gamer.setLastName(i.getString());
                            } else if (i.getFieldName().equals("firstName")) {
                                gamer.setFirstName(i.getString());
                            } else if (i.getFieldName().equals("sex")) {
                                gamer.setSex(i.getString());
                            } else if (i.getFieldName().equals("birthday")) {
                                gamer.setBirthday(i.getString());
                            } else if (i.getFieldName().equals("whiteZones")) {
                                gamer.setWhiteZones(i.getString());
                            } else if (i.getFieldName().equals("phone")) {
                                gamer.setPhone(i.getString());
                            } else if (i.getFieldName().equals("email")) {
                                gamer.setEmail(i.getString());
                            }

                        } else {

                            if (i.getContentType().equals("image/jpeg")) {

                                File img_file = null;
                                String path = "";
                                do {
                                    path = servletContext.getRealPath("/img/" + rand.nextInt(100000) + ".jpg");
                                    img_file = new File(path);
                                } while (img_file.exists());


                                img_file.createNewFile();

                                i.write(img_file);

                                if (gamer.getPhotoPath().length() > 0) {
                                    path = gamer.getPhotoPath() + ";" + path;
                                }
                                gamer.setPhotoPath(path);

                            }
                        }
                    }

                    gamer.setLifeCode(new LCGenerator().generateLC());
                    gamer.setDateTimeImmunitet(new Date(76, 7, 21));

                    model.getGamersDAO().insert(gamer);

                    url = "StartPage?operation=admin&adminPassword=Gh@dbk0";

                } catch (FileUploadException ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (operation.equals("changeNewsStatus")) {      //изменение статуса новости
            
            model.getNewsDAO().updateNewStatus(Integer.parseInt(request.getParameter("idNews")), request.getParameter("statusNews"));
            url = "StartPage?operation=admin&adminPassword=Gh@dbk0";
            
        } else if (operation.equals("addNews")) {               //добавление новости
            
            if (request.getParameter("textNews").length() > 0) {
                News nw = new News(request.getParameter("textNews"), NewState.NOTACTIVE, new Date());
                model.getNewsDAO().insert(nw);
                url = "StartPage?operation=admin&adminPassword=Gh@dbk0";
            }
            
        } else if (operation.equals("editGamer")) {             //редактирование данных игрока администратором

            UserPageForm form = new UserPageForm(request.getParameter("id"),
                    request.getParameter("nickName"), request.getParameter("firstName"),
                    request.getParameter("lastName"), request.getParameter("sex"),
                    request.getParameter("birthday"), request.getParameter("phone"),
                    request.getParameter("email"), request.getParameter("lifeCode"),
                    request.getParameter("stringDateTimeImmunitet"), request.getParameter("idVictim"),
                    request.getParameter("status"), request.getParameter("whiteZones"));

            Gamers gamer = model.getGamersDAO().selectByID(Integer.parseInt(form.getId()));

            if (!gamer.getNickName().equals(form.getNick())) {
                gamer.setNickName(form.getNick());
            }
            if (!gamer.getFirstName().equals(form.getFirstName())) {
                gamer.setFirstName(form.getFirstName());
            }
            if (!gamer.getLastName().equals(form.getLastName())) {
                gamer.setLastName(form.getLastName());
            }
            if (!gamer.getSex().equals(form.getSex())) {
                gamer.setSex(form.getSex());
            }
            if (!gamer.getBirthday().equals(form.getBirthday())) {
                gamer.setBirthday(form.getBirthday());
            }
            if (!gamer.getPhone().equals(form.getPhone())) {
                gamer.setPhone(form.getPhone());
            }
            if (!gamer.getEmail().equals(form.getEmail())) {
                gamer.setEmail(form.getEmail());
            }
            if (!gamer.getLifeCode().equals(form.getLifeCode())) {
                gamer.setLifeCode(form.getLifeCode());
            }

            if (!gamer.getStringDateTimeImmunitet().equals(form.getImmDateTime())) {
                gamer.setStringDateTimeImmunitet(form.getImmDateTime());
            }

            if (gamer.getIdVictim() != Integer.parseInt(form.getIdVictim())) {
                gamer.setIdVictim(Integer.parseInt(form.getIdVictim()));
            }
            if (!gamer.getWhiteZones().equals(form.getWhiteZones())) {
                gamer.setWhiteZones(form.getWhiteZones());
            }
            if (!gamer.getLifeCode().equals(form.getLifeCode())) {
                gamer.setLifeCode(form.getLifeCode());
            }


            if (!gamer.getStatus().equals(form.getStatus())) {

                if (gamer.getStts() == GamerState.ACTIVE && form.getStatus().equals("DISQUALIFIED")) {
                    Gamers hunt = model.getGamersDAO().selectByIDVictim(gamer.getId());
                    hunt.setIdVictim(gamer.getIdVictim());
                    model.getGamersDAO().update(hunt);
                    gamer.setIdVictim(0);
                }


                gamer.setStatus(form.getStatus());

            }

            model.getGamersDAO().update(gamer);

            url = "StartPage?operation=admin&adminPassword=Gh@dbk0";
            
        } else if (operation.equals("editPenalty")) {           //редактирование явки

            Penaltys p = model.getPenaltysDAO().selectActiveByIDGamer(Integer.parseInt(request.getParameter("idGamer")));
            p.setStatus(request.getParameter("status"));
            model.getPenaltysDAO().update(p);

            url = "StartPage?operation=admin&adminPassword=Gh@dbk0";

        } else if (operation.equals("addPenalty")) {            //назначение явки

            int idGamer = Integer.parseInt(request.getParameter("idGamer"));
            
            Penaltys pnlt = new Penaltys(idGamer, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(request.getParameter("dateTimeMeeting")), getLatin1String(request.getParameter("place")), PenaltyState.ACTIVE);
            Gamers h = model.getGamersDAO().selectByIDVictim(idGamer);
            Messages msg = new Messages(h.getId(), 9999, MessageSenderState.IS, new Date(), getLatin1String("Вашей жертве назначена явка"), MessageState.NEW);
            Gameevents e = new Gameevents(new Date(), idGamer, h.getId(), EventState.PENALTY);

            model.getPenaltysDAO().insert(pnlt);
            model.getMessagesDAO().insert(msg);
            model.getGameEventsDAO().insert(e);


            url = "StartPage?operation=admin&adminPassword=Gh@dbk0";
            
        } else if (operation.equals("sendAdminMessage")) {      //отправка сообщения игроку от администратора

            Messages msg = new Messages(Integer.parseInt(request.getParameter("idGamer")), 0, MessageSenderState.ADMIN, new Date(), getLatin1String(request.getParameter("textMsg")), MessageState.NEW);

            model.getMessagesDAO().insert(msg);

            url = "StartPage?operation=admin&adminPassword=Gh@dbk0";
        }

        //переход по соответствующему url
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
    
    private String getLatin1String(String in){
        try {
            return new String(in.getBytes(),"latin1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
