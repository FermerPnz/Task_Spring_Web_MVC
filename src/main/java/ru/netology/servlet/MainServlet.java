//package ru.netology.servlet;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import ru.netology.controller.PostController;
//
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class MainServlet extends HttpServlet {
//
//    // Выносим строковые константы в поля класса
//    private static final String GET_METHOD = "GET";
//    private static final String POST_METHOD = "POST";
//    private static final String DELETE_METHOD = "DELETE";
//    private static final String API_POSTS_PATH = "/api/posts";
//    private static final String API_POSTS_ID_REGEX = "/api/posts/\\d+";
//
//    private PostController controller;
//
//    @Autowired
//    public MainServlet(PostController controller) {
//        this.controller = controller;
//    }
//
//
//    @Override
//    public void init() {
//        final var context = new AnnotationConfigApplicationContext();
//        controller = context.getBean(PostController.class);
//    }
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            final var path = req.getRequestURI();
//            final var method = req.getMethod();
//
//            // Обработка GET /api/posts
//            if (method.equals(GET_METHOD) && path.equals(API_POSTS_PATH)) {
//                controller.all(resp);
//                return;
//            }
//
//            // Обработка GET /api/posts/{id}
//            if (method.equals(GET_METHOD) && path.matches(API_POSTS_ID_REGEX)) {
//                final var id = parseId(path);
//                controller.getById(id, resp);
//                return;
//            }
//
//            // Обработка POST /api/posts
//            if (method.equals(POST_METHOD) && path.equals(API_POSTS_PATH)) {
//                controller.save(req.getReader(), resp);
//                return;
//            }
//
//            // Обработка DELETE /api/posts/{id}
//            if (method.equals(DELETE_METHOD) && path.matches(API_POSTS_ID_REGEX)) {
//                final var id = parseId(path);
//                controller.removeById(id, resp);
//                return;
//            }
//
//            // Если запрос не соответствует ни одному шаблону
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Метод для извлечения ID из пути
//    public long parseId(String path) {
//        return Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
//    }
//}