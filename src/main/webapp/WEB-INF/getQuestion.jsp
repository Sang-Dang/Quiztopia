<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%
    String questionNo = request.getParameter("question_no");
    String req = request.getParameter("req");
    String set = (String) session.getAttribute("set");
    String email = (String) session.getAttribute("email");

    if (req != null) {
        if (req.equals("first")) {
            String result = UserDAO.getQuestion(questionNo, set);
            out.print(result);
        } else if (req.equals("next")) {
            String questionId = request.getParameter("question_id");
            String answer = request.getParameter("answer");
            LinkedHashMap<String, String> lhm = (LinkedHashMap<String, String>) session.getAttribute("all_answers");

            if (!answer.equals("undefined")) {
                if (lhm == null) {
                    lhm = new LinkedHashMap<>();
                }
                lhm.put(questionId, answer);
                session.setAttribute("all_answers", lhm);
            }
            String result = UserDAO.getQuestion(questionNo, set);
            out.print(result);
        }
    } else {
        String questionId = request.getParameter("question_id");
        String answer = request.getParameter("answer");
        LinkedHashMap<String, String> lhm = (LinkedHashMap<String, String>) session.getAttribute("all_answers");

        if (!answer.equals("undefined")) {
            if (lhm == null) {
                lhm = new LinkedHashMap<>();
            }
            lhm.put(questionId, answer);
            session.setAttribute("all_answers", lhm);
        }

        String result = UserDAO.getQuestion(questionNo, set);
        out.print(result);
    }
%>