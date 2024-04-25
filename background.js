// // Background script

// // Listen for messages from the popup script
// chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
//     if (request.enabled !== undefined) {
//       // Save the enabled state in localStorage
//       localStorage.setItem('enabled', request.enabled);
//     }
//   });
  
//   // Initialize the extension state
//   var isEnabled = localStorage.getItem('enabled') === 'true';
//   // Send a message to the content script to enable or disable it based on the saved state
//   chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
//     var tabId = tabs[0].id;
//     chrome.tabs.sendMessage(tabId, {enabled: isEnabled});
//   });
  console.log("Hello");