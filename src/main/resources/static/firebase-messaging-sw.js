importScripts("https://www.gstatic.com/firebasejs/7.16.1/firebase-app.js");
importScripts(
    "https://www.gstatic.com/firebasejs/7.16.1/firebase-messaging.js",
);
// For an optimal experience using Cloud Messaging, also add the Firebase SDK for Analytics.
importScripts(
    "https://www.gstatic.com/firebasejs/7.16.1/firebase-analytics.js",
);

// Initialize the Firebase app in the service worker by passing in the
// messagingSenderId.
firebase.initializeApp({
    messagingSenderId: "911209797607",
    apiKey: "AIzaSyCFFA4_iFJuCx2SzTHk8sU8I8VJ5Rg_6XY",
    projectId: "luxurywatch-315607",
    appId:  "1:911209797607:web:0c69280ea815641f538f42",
});
var linkEven = ""
// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();
// self.addEventListener('notificationclick', function(event){
//     event.notification.close();
//     event.waitUntil(
//             clients.openWindow("https://demo-thongbao.herokuapp.com"));
// });
self.addEventListener('notificationclick', function(event){
    event.notification.close();
    event.waitUntil(
            clients.openWindow(linkEven));
});
messaging.setBackgroundMessageHandler(function(payload) {
    linkEven = payload.data.link;
    console.log(
        "[firebase-messaging-sw.js] Received background message ",
        payload,
    );
    // Customize notification here
    const notificationTitle = payload.data.title;
    const notificationOptions = {
        body: payload.data.content,
        icon: "/itwonders-web-logo.png",
    };
    return self.registration.showNotification(
        notificationTitle,
        notificationOptions,
    );
});