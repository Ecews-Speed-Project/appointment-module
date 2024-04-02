document.addEventListener("DOMContentLoaded", () => {
    fetch("/ms/uiframework/resource/nmrsappointment/scripts/patientAppointments.json")
        .then(response => response.json())
        .then(data => {
            const todayAppt = document.getElementById("todayAppt");
            let tableRow = '';
            const currentDate = new Date().toISOString().slice(0, 10);
            const currentAppt = data.filter(records => records.date == currentDate);
            currentAppt.slice(0, 8).map(patient => {
                const statusSpan = patient.status === "Pending"
                    ? `<span class="bg-info-subtle status-span">${patient.status}</span>`
                    : patient.status === "Appointment kept"
                        ? `<span class="bg-success-subtle status-span">${patient.status}</span>`
                        : patient.status === "Missed"
                            ? `<span class="bg-danger-subtle status-span">${patient.status}</span>`
                            : null;

                const base_fp = patient.baseline === "Yes" ? `<i class="fa-solid fa-fingerprint"></i>`
                    : null;

                const baseline = currentAppt.filter(record => record.baseline === "Yes").length;
                const recapture = currentAppt.filter(record => record.recapture === "Yes").length;
                const recapture_percent = Math.round((recapture / baseline) * 100);
                tableRow += `<tr>
               <td>
                <span class="name-bold">${patient.id}</span>
                <span class="inline-block float-end icon fs-4">${base_fp}</span>
                <p class="patientName hidden">${patient.name}</p>
                <p class="fw-bold">**********</p>
               </td>
               <td>${patient.apptType} Appointment</td>
               <td>${statusSpan}</td>
               <td>${patient.date}</td>
               <td><a href="#">View details</a></td>
           </tr>`;
            });

            todayAppt.innerHTML = tableRow;
        })
});

function toggleNameShow() {
    const patientNames = document.querySelectorAll(".patientName");
    const viewIcon = document.getElementById("viewIcon");

    patientNames.forEach(patientName => {
        if (patientName.style.display === 'none' || patientName.style.display === '') {
            patientName.style.display = "block";
            patientName.nextElementSibling.style.display = "none"; // Hide the hidden text next to the name
            viewIcon.innerHTML = `<i class="fa-solid fa-eye-slash"></i>`;
        } else {
            patientName.style.display = "none";
            patientName.nextElementSibling.style.display = "block"; // Show the hidden text next to the name
            viewIcon.innerHTML = `<i class="fa-solid fa-eye"></i>`;
        }
    });
}

const openAppt = () => {
    const newAppt = document.getElementById('newAppt');
    newAppt.style.display = "block";
    const search_result = document.querySelector(".pat-search-and-result");
    const newPatientAppt = document.querySelector(".pat-new-appt");
    search_result.style.display = "block";
    newPatientAppt.style.display = "none";
    getProviders();
}

const closeAppt = () => {
    const newAppt = document.getElementById('newAppt');
    const search_result = document.querySelector(".pat-search-result");
    const search_input = document.querySelector(".pat-search-input");
    newAppt.style.display = "none";
    search_result.style.display = "none";
    search_input.value = '';
}

function findAppointmentPatientServer(searchTextServer) {
    jq = jQuery;
    let searchResults = document.querySelector(".pat-search-result");
    let url = "/nmrsappointment/users/fetchPatient.action";

    let searchDisplay = '';
    if (searchTextServer.length >= 2) {
        jq.ajax({
            url: url,
            dataType: "json",
            data: {
                'searchText' : searchTextServer
            }
        }).success(function(res)
        {
            var responseObject = JSON.parse(res);

            console.log( responseObject.length)
         /*   if (responseObject.length < 1) {
                searchDisplay = `<h1>No Patient Found!</h1>`;
                searchResults.style.display = 'block';
                searchResults.innerHTML = searchDisplay;
            }else{
                console.log(res)
            }*/

        })

    } else {
        searchDisplay += '';
        searchResults.innerHTML = searchDisplay;
    }

}

function findAppointmentPatient(searchText) {
    let searchResults = document.querySelector(".pat-search-result");
    let searchDisplay = '';
    if (searchText.length >= 2) {
        fetch("/ms/uiframework/resource/nmrsappointment/scripts/patientAppointments.json")
            .then(response => response.json())
            .then(data => {
                const filteredPatients = data.filter(patient => patient.name.toLowerCase().includes(searchText.toLowerCase()));
                const currentPatient = (id) => data.filter(patient => patient.id === id);

                if (filteredPatients.length < 1) {
                    searchDisplay = `<h1>No Patient Found!</h1>`;
                    searchResults.style.display = 'block';
                    searchResults.innerHTML = searchDisplay;
                } else {
                    searchResults.style.display = 'block';
                    filteredPatients.sort((a, b) => a.name > b.name ? 1 : -1).map(patient => {
                        searchDisplay += `
                                <p class="patientId py-3 px-2 border-bottom border-success-subtle"
                                    data-patient-id=${patient.id}>
                                    ${patient.name}
                                </p>
                            `;

                    });

                    searchResults.innerHTML = searchDisplay;

                    const patientNameElements = document.querySelectorAll('.patientId');
                    patientNameElements.forEach(element => {
                        element.addEventListener('click', () => {
                            const patientId = element.getAttribute('data-patient-id');
                            if (localStorage.getItem('CurrentPatient')) {
                                // If the item exists, delete it
                                localStorage.removeItem('CurrentPatient');
                            }
                            // Add a new item to localStorage
                            const cur_patient = JSON.stringify(currentPatient(patientId));
                            localStorage.setItem('CurrentPatient', cur_patient);

                            const search_result = document.querySelector(".pat-search-and-result");
                            const searchResults = document.querySelector(".pat-search-result");
                            const search_input = document.querySelector(".pat-search-input");
                            const newPatientAppt = document.querySelector(".pat-new-appt");
                            search_result.style.display = 'none';
                            newPatientAppt.style.display = 'block';
                            search_input.value = '';
                            searchResults.style.display = 'none';

                            const patient_details = document.querySelector(".pat-new-appt-head");
                            const pdata = JSON.parse(localStorage.getItem("CurrentPatient"));
                            const isPBS = pdata[0].baseline === "Yes" ?
                                `<i class="fa-solid fa-fingerprint"></i>` : "No Base PBS";
                            patient_details.innerHTML = `
                                               <p>${pdata[0].name} ${isPBS}</p>
                                           `;
                        });
                    });
                }


            })

    } else {
        searchDisplay += '';
        searchResults.innerHTML = searchDisplay;
    }

}

function getProviders() {
    const baseUrl = window.location.origin;
    const providerName = document.getElementById("providerName");
    let providerArea = '<option></option>';

    fetch(`${baseUrl}/openmrs/ws/rest/v1/provider`)
        .then(response => response.json())
        .then(data => {
            data.results.map(provider => {
                providerArea += `
                    <option>${provider.display}</option>
                `
            });

            providerName.innerHTML = providerArea;
        })

}


