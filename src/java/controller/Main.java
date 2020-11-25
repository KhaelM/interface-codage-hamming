/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import codage.Hamming;
import static codage.Hamming.corriger;
import static codage.Hamming.encoderResultat;
import static codage.Hamming.genererPositionErreur;
import static codage.Hamming.genererTailleErreur;
import static codage.Hamming.getMotFinal;
import static codage.Hamming.insererErreur;
import static codage.Hamming.trouverPositionErreur;
import codage.Resultat;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miker
 */
@WebServlet(name = "Main", urlPatterns = {"/"})
public class Main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String texte = request.getParameter("texte");
        if (texte != null) {
            byte[] bytes = texte.getBytes("UTF-8");

            Resultat[] envois = new Resultat[bytes.length];
            System.out.println("========== Envoyé:");
            int i = 0;
            for (i = 0; i < bytes.length; i++) {
                Resultat envoi = encoderResultat(bytes[i]);
                System.out.println(envoi.getCaractere() + " | " + envoi.getBinaire() + " | " + envoi.getHamming());
                envois[i] = envoi;
            }
            System.out.println("========== Envoyé: " + getMotFinal(envois));

            request.setAttribute("envoye", getMotFinal(envois));
            request.setAttribute("envois", envois);

            Resultat[] receptions = new Resultat[bytes.length];
            System.out.println("========== Réception:");
            for (i = 0; i < envois.length; i++) {
                Resultat reception = new Resultat(envois[i]);
                int positions[] = new int[genererTailleErreur()];
                for (int j = 0; j < positions.length; j++) {
                    positions[j] = genererPositionErreur();
                }
                Hamming.insererErreur(reception.getMotDeCode(), positions);
                System.out.println(reception.getCaractere() + " | " + reception.getBinaire() + " | " + reception.getHamming());
                receptions[i] = reception;
            }
            System.out.println("========== Réception: " + getMotFinal(receptions));

            request.setAttribute("reception", getMotFinal(receptions));
            request.setAttribute("receptions", receptions);

            Resultat[] corrections = new Resultat[bytes.length];
            System.out.println("========== Correction:");
            for (i = 0; i < receptions.length; i++) {
                Resultat correction = new Resultat(receptions[i]);
                correction.setPositionErreur(trouverPositionErreur(correction.getMotDeCode()));
                corriger(correction.getMotDeCode());
                System.out.println(correction.getCaractere() + " | " + correction.getBinaire() + " | " + correction.getHamming() + " | " + correction.getPositionErreur());
                corrections[i] = correction;
            }
            System.out.println("========== Correction: " + getMotFinal(corrections));

            request.setAttribute("correction", getMotFinal(corrections));
            request.setAttribute("corrections", corrections);
        }

        request.getRequestDispatcher("hamming.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
