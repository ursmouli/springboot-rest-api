package com.app.restapi.service;

import com.app.restapi.dto.TimetableDayRowDto;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import java.util.List;


@Service
public class TimetablePdfService {

    private final TemplateEngine templateEngine;
    public TimetablePdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdfFromTemplate(List<TimetableDayRowDto> entries, String sectionName) {
        Context context = new Context();
        context.setVariable("entries", entries);
        context.setVariable("sectionName", sectionName);

        String htmlContent = templateEngine.process("timetable", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, target);

        return target.toByteArray();
    }

//    public byte[] generateTimetablePdf(List<TimetableEntry> entries, String sectionName) {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document(PageSize.A4.rotate()); // Landscape is better for timetables
//
//        try {
//            PdfWriter.getInstance(document, out);
//            document.open();
//
//            // Title
//            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
//            Paragraph title = new Paragraph("Timetable for Section: " + sectionName, fontTitle);
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//            document.add(Chunk.NEWLINE);
//
//            // Table setup (6 columns: Day, Period, Subject, Teacher, Room)
//            PdfPTable table = new PdfPTable(5);
//            table.setWidthPercentage(100);
//
//            // Header Row
//            String[] headers = {"Day", "Period", "Subject", "Teacher", "Room"};
//            for (String header : headers) {
//                PdfPCell cell = new PdfPCell(new Paragraph(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);
//            }
//
//            // Data Rows
//            for (TimetableEntry entry : entries) {
//                table.addCell(entry.getDayOfWeek().toString());
//                table.addCell(String.valueOf(entry.getPeriodNumber()));
//                table.addCell(entry.getSubjectName());
//                table.addCell(entry.getTeacherName());
//                table.addCell(entry.getClassroom());
//            }
//
//            document.add(table);
//            document.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//
//        return out.toByteArray();
//    }
}
