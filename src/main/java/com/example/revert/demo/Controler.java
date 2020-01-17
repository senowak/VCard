package com.example.revert.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezvcard.VCard;
import ezvcard.property.StructuredName;

@Controller
public class Controler {

    ArrayList<Result> results = new ArrayList<>();

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String cardList(@RequestParam(defaultValue = "") String name, final Model model) {
        String link = "https://adm.edu.p.lodz.pl/user/";
        String user = "users.php";
        String search = "?search=";

        String url = link + user + search + name;

        try {
            results.clear();
            Document HtmlDocument = Jsoup.connect(url)
                                         .get();
            Elements elements = HtmlDocument.getElementsByClass("user-info");
            for (Element element : elements) {
                Result result = new Result();
                result.setFullName(element.selectFirst("h3")
                                          .text());
                result.setOrganizationUnit(element.selectFirst(".item-content")
                                                  .text());
                result.setTitle(element.selectFirst("h4")
                                       .text());
                System.out.println(result.getFullName());
                results.add(result);
            }
            model.addAttribute("resultsList", results);
            model.addAttribute("name", name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "results";
    }

    @GetMapping("/download/{fullName}")
    public void download(@PathVariable String fullName, HttpServletResponse response, Model model) {
        for (Result result : results) {
            if (result.getFullName()
                      .equals(fullName)) {
                VCard vcard = new VCard();
                StructuredName n = new StructuredName();
                n.setGiven(fullName.substring(0, fullName.indexOf(" ")));
                n.setFamily(fullName.substring(fullName.indexOf(" ") + 1));
                n.getPrefixes()
                 .add(result.getTitle());
                vcard.setStructuredName(n);
                vcard.setOrganization(result.getOrganizationUnit());

                String vcardName = fullName + ".vcf";

                try {
                    vcard.write(new File(vcardName));
                    File fileToDownload = new File(vcardName);
                    InputStream inputStream = new FileInputStream(fileToDownload);
                    response.setContentType("application/force-download");
                    response.setHeader("Content-Disposition", "attachment; filename=" + vcardName);
                    IOUtils.copy(inputStream, response.getOutputStream());
                    response.flushBuffer();
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
