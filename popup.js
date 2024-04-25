// Popup script

// Get the checkbox element
var toggleExtensionCheckbox = document.getElementById('toggleExtension');

// Initialize the extension state
var isEnabled = localStorage.getItem('enabled') === 'true';
toggleExtensionCheckbox.checked = isEnabled;

// Toggle extension state when the checkbox changes
toggleExtensionCheckbox.addEventListener('change', function() {
  var isChecked = toggleExtensionCheckbox.checked;
  localStorage.setItem('enabled', isChecked);
  // Send a message to the background script to enable or disable the content script
  chrome.runtime.sendMessage({enabled: isChecked});
});
