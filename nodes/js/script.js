document.addEventListener("DOMContentLoaded", () => {
    const menuItems = document.querySelectorAll(".sidebar ul li a");
    const contentArea = document.getElementById("dynamic-content");

    // Add click event listeners to menu items
    menuItems.forEach(item => {
        item.addEventListener("click", (event) => {
            event.preventDefault();
            const pageUrl = event.target.getAttribute("data-page");

            // Load the content dynamically
            if (pageUrl) {
                fetch(pageUrl)
                    .then(response => response.text())
                    .then(html => {
                        contentArea.innerHTML = html;
                        populateNodes(); // Reinitialize nodes after loading new content
                    })
                    .catch(error => console.error(error));
            }
        });
    });

    // Function to populate nodes dynamically
    function populateNodes() {
        const emails = [
           "elhlawy@gmail.com",
  "java.developer.mohamed@gmail.com",
  "acealbastoni@gmail.com",
  "aboarram1@gmail.com",
  "mecoreda56@gmail.com",
  "aceneny@gmail.com",
  "emergency.qmedic@gmail.com",
  "moabd742@gmail.com",
  "mohamedabdelhamid88888@gmail.com",
  "wafaa19911014@gmail.com",
  "mohamed92.love.wafaa91@gmail.com",
  "moabdelhamead898@gmail.com",
  "acealbastony@gmail.com",
  "elwensh250@gmail.com",
  "28Feb2022AceAlBastoni@gmail.com",
  "mabdelhamid@smartec-group.com",
  "08jan2023@gmail.com",
  "12Jan2023@gmail.com",
  "mabdelhamidgamea@gmail.com",
  "22jan2023@gmail.com",
  "object.getclass@gmail.com",
  "math.getRandom@gmail.com",
  "mohasaber954@gmail.com",
  "azt60713@gmail.com",
  "mabdelhamid1992@gmail.com",
  "my.name.is.ace.albastoni@gmail.com",
  "applicationcontextacealbastoni@gmail.com",
  "object.tostring.acealbastoni@gmail.com",
  "math.getrandom.acealbastoni@gmail.com",
  "hibernate.cfg.xml.acealbastoni@gmail.com",
  "hibernate.cfg.xml.acealbastoni1@gmail.com",
  "application.acealbastoni11@gmail.com",
  "elhlawy@gmail.com",
  "java.developer.mohamed@gmail.com"
        ];

        


        const nodeList = document.getElementById("node-list");

        if (nodeList) {
            nodeList.innerHTML = ""; // Clear existing nodes
    
            // Create a textarea for displaying job details
            const jobDetailsTextArea = document.createElement("textarea");
            jobDetailsTextArea.id = "job-details";
            jobDetailsTextArea.placeholder = "Hover over an email to see job details...";
            jobDetailsTextArea.rows = 4;
            jobDetailsTextArea.cols = 40;
            jobDetailsTextArea.readOnly = true;
            jobDetailsTextArea.style.position = "absolute"; // Allow dynamic positioning
            jobDetailsTextArea.style.display = "none"; // Hide initially
            document.body.appendChild(jobDetailsTextArea);
    
            emails.forEach(email => {
                const li = document.createElement("li");
                li.className = "node-item"; // Add a class for styling
                li.innerHTML = `
                    <span class="email-item">${email}</span>
                    <div class="toggle-container">
                        <label class="toggle-switch">
                            <input type="checkbox" class="node-toggle">
                            <span class="slider"></span>
                        </label>
                    </div>
                `;
                nodeList.appendChild(li);
    
                // Add event listeners for hover to show job details
                const emailItem = li.querySelector(".email-item");
    
                emailItem.addEventListener("mouseover", (event) => {
                    // Replace with actual job details logic
                    jobDetailsTextArea.value = `Details about the job for ${email}`;
                    jobDetailsTextArea.style.left = `${event.pageX + 10}px`; // Position near the mouse
                    jobDetailsTextArea.style.top = `${event.pageY + 10}px`;
                    jobDetailsTextArea.style.display = "block"; // Show textarea
                });
    
                emailItem.addEventListener("mouseout", () => {
                    jobDetailsTextArea.style.display = "none"; // Hide textarea when not hovering
                });
            });
    
            // Setup master toggle functionality after populating nodes
            setupMasterToggle();
        }




    }

    // Master toggle functionality
    function setupMasterToggle() {
        const masterToggle = document.getElementById("master-toggle");
        const nodeToggles = document.querySelectorAll(".node-toggle");

        if (masterToggle) {
            masterToggle.addEventListener("change", () => {
                const isChecked = masterToggle.checked;
                nodeToggles.forEach(toggle => toggle.checked = isChecked);
            });

            // Sync master toggle state based on individual toggles
            nodeToggles.forEach(toggle => {
                toggle.addEventListener("change", () => {
                    const allChecked = Array.from(nodeToggles).every(toggle => toggle.checked);
                    const noneChecked = Array.from(nodeToggles).every(toggle => !toggle.checked);

                    if (allChecked) {
                        masterToggle.checked = true;
                        masterToggle.indeterminate = false;
                    } else if (noneChecked) {
                        masterToggle.checked = false;
                        masterToggle.indeterminate = false;
                    } else {
                        masterToggle.indeterminate = true;
                    }
                });
            });
        }
    }

    // Populate nodes on initial load
    populateNodes();
});

document.addEventListener("DOMContentLoaded", () => {
    const dropdownToggle = document.querySelector(".dropdown-toggle");
    const dropdownMenu = document.querySelector(".dropdown-menu");

    // Toggle the dropdown on click
    dropdownToggle.addEventListener("click", (event) => {
        event.preventDefault(); // Prevent default link behavior
        dropdownMenu.classList.toggle("open");
        dropdownMenu.style.display = dropdownMenu.style.display === "block" ? "none" : "block";
    });

    // Close the dropdown if clicked outside
    document.addEventListener("click", (event) => {
        if (!event.target.closest(".dropdown")) {
            dropdownMenu.style.display = "none";
        }
    });
});




document.addEventListener("DOMContentLoaded", () => {
    const expandableItems = document.querySelectorAll(".expandable");

    expandableItems.forEach(item => {
        const toggle = item.querySelector(".expand-toggle");
        const submenu = item.querySelector(".sub-menu");
        toggle.addEventListener("click", (e) => {
            e.preventDefault();
            item.classList.toggle("expanded");

            // Animate the submenu
            if (item.classList.contains("expanded")) {
                submenu.style.maxHeight = submenu.scrollHeight + "px";
            } else {
                submenu.style.maxHeight = "0";
            }
        });
    });
});


document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("messageModal");
    const trigger = document.getElementById("newMessagesTrigger");
    const closeBtn = document.querySelector(".modal .close");

    // Open modal on trigger click
    trigger.addEventListener("click", () => {
        modal.style.display = "block";
    });

    // Close modal on close button click
    closeBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Close modal if clicking outside of modal content
    window.addEventListener("click", (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});







document.addEventListener("DOMContentLoaded", () => {
    // Default page to load
    const defaultPage = "pages/dashboard.html";
    const contentArea = document.getElementById("dynamic-content");

    // Fetch and load the default page
    fetch(defaultPage)
    .then(response => {
        if (!response.ok) {
            throw new Error(`Failed to load ${defaultPage}`);
        }
        return response.text();
    })
    .then(html => {
        contentArea.innerHTML = html;
        })
        .catch(error => {
            console.error("Error loading default page:", error);
        });
});
