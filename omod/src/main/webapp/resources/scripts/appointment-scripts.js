document.addEventListener("DOMContentLoaded", () => {

    fetchAppointments()

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


const createAppointment = () => {
    jq = jQuery;
    let url = "/nmrsappointment/users/saveAppointments.action";

    const pdata = JSON.parse(localStorage.getItem("CurrentPatient"));
    const apptNotes =  jq('#apptNotes').val();
    const apptType =jq('#apptType').val();
    const apptDate = jq('#apptDate').val();
    const visitDate = jq('#visitDate').val();
    const patientId = pdata[0].patientId;

     var data =  {
         'patientId': patientId,
         'visitDate': visitDate,
         'comments': apptNotes,
         'type': apptType,
         'appointmentDate': apptDate,
     }

    jq.ajax({
        url: url,
        dataType: "json",
        data: data

    }).success(function (filename) {
        location.reload();
    }).error(function (xhr, status, err) {
            if (status === 'timeout') {
                alert("the export will take a while, the list will be updated when it's done");
            } else {
                alert('There was an error generating all NDR files, check generated files at downloads directory in the application root folder ' + err);
            }//popupDialog.close();
           // jq('#gen-wait').hide();

        });

}

const closeAppt = () => {
    const newAppt = document.getElementById('newAppt');
    const search_result = document.querySelector(".pat-search-result");
    const search_input = document.querySelector(".pat-search-input");
    newAppt.style.display = "none";
    search_result.style.display = "none";
    search_input.value = '';
}

//format date
const formatDate = (dateString) =>{

    const date = new Date(dateString);
    date.setUTCHours(date.getUTCHours() + 1);
    const formattedDate = date.toISOString().split('T')[0];

    return formattedDate;
}

//change to title case

function titleCase(str) {
    return str.toLowerCase().split(' ').map(function (word) {
        return (word.charAt(0).toUpperCase() + word.slice(1));
    }).join(' ');
}

function fetchAppointments() {
    const baseUrl = window.location.origin;
    const endPoint = '/openmrs/coreapps/clinicianfacing/patient.page?patientId='
    jq = jQuery;
    let searchResults = 'sdsd';
    let url = "/nmrsappointment/users/fetchAppointments.action";
    jq.ajax({
        url: url,
        dataType: "json",
        data: {
            'searchText': searchResults
        }
    }).success(function (data) {
        const todayApt = JSON.stringify(data)
        sessionStorage.removeItem("filterAppointment");
        sessionStorage.setItem("todayAppointment", todayApt);
        sessionStorage.setItem("isSessionData", true)

        const todayAppt = document.getElementById("todayAppt");
        const apptValue = document.getElementById("apptValue");
        const baseValue = document.getElementById("baseValue");
        const recaptureValue = document.getElementById("recaptureValue");
        const pbsPercent = document.getElementById("pbsPercent");
        const noPbs = document.getElementById("noPbs");
        const noPbsPercent = document.getElementById("noPbsPercent");

        const pbs = data.filter(pbs => pbs.baseline === 'Yes').length;
        const recapture = data.filter(pbs => pbs.recapture === 'Yes').length
        //populate the values
        apptValue.innerText = data.length;
        baseValue.innerText = pbs;
        recaptureValue.innerText = recapture;
        pbsPercent.innerText = Math.round((recapture / pbs) * 100) + "%";
        noPbs.innerText =  pbs - recapture;
        noPbsPercent.innerText = Math.round((pbs - recapture) / pbs  * 100) + "%";
        let tableRow = '';
        // const currentDate = new Date().toISOString().slice(0, 10);
        // const currentAppt = data.filter(records => records.date == currentDate);
        data.sort((a,b) => a.nextAppointmentDate > b.nextAppointmentDate ? -1 : 1).map(patient => {
            const statusSpan = patient.status === "Pending"
                ? `<span class="bg-info-subtle status-span">${patient.status}</span>`
                : patient.status === "Appointment kept"
                    ? `<span class="bg-success-subtle status-span">${patient.status}</span>`
                    : patient.status === "Missed"
                        ? `<span class="bg-danger-subtle status-span">${patient.status}</span>`
                        : null;

            const base_fp = patient.baseline === "Yes" ? `<i class="fa-solid fa-fingerprint"></i>`
                : null;


            tableRow += `<tr>
               <td>
                <span class="name-bold">${patient.identifier}</span>
                <span class="inline-block float-end icon fs-4">${base_fp}</span>
                <p class="patientName hidden">${patient.patientName}</p>
                <p class="fw-bold">**********</p>
               </td>
               <td>${titleCase(patient.appointmentType)} Appointment</td>
               <td>${statusSpan}</td>
               <td>${formatDate(patient.nextAppointmentDate)}</td>
               <td><a href="/openmrs/coreapps/clinicianfacing/patient.page?patientId=${patient.lunchView.replace(/_/g, "-")}">View details</a></td>
           </tr>`;
        });

        todayAppt.innerHTML = tableRow;
    })
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
                'searchText': searchTextServer
            }
        }).success(function (res) {
            const currentPatient = (id) => res.filter(patient => patient.patientId == id);

            if (res.length < 1) {
                searchDisplay = `<h1>No Patient Found!</h1>`;
                searchResults.style.display = 'block';
                searchResults.innerHTML = searchDisplay;
            } else {
                searchResults.style.display = 'block';
                res.sort((a, b) => a.name > b.name ? 1 : -1).map(patient => {
                    searchDisplay += `
                                <p class="patientId py-3 px-2 border-bottom border-success-subtle"
                                    data-patient-id=${patient.patientId}>
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
                        const curPatient = JSON.stringify(currentPatient(patientId));

                        localStorage.setItem('CurrentPatient', curPatient);

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

function filterPatientAppointment() {
    let jq = jQuery;
    const startDate = jq("#startDate").val();
    const endDate = jq("#endDate").val();
    let filterResults = document.querySelector(".pat-search-result");
    let filterDisplay = '';
        fetch("/ms/uiframework/resource/nmrsappointment/scripts/patientAppointments.json")
            .then(response => response.json())
            .then(data => {
                const filteredPatients = data.filter(patient => patient.date >= startDate && patient.date <= endDate);
                const filterApt = JSON.stringify(filteredPatients)
                sessionStorage.removeItem("todayAppointment");
                sessionStorage.setItem("filterAppointment", filterApt);
                sessionStorage.setItem("isSessionData", true)

                       const todayAppt = document.getElementById("todayAppt");
                          const apptValue = document.getElementById("apptValue");
                          const baseValue = document.getElementById("baseValue");
                          const recaptureValue = document.getElementById("recaptureValue");
                          const pbsPercent = document.getElementById("pbsPercent");
                          const noPbs = document.getElementById("noPbs");
                          const noPbsPercent = document.getElementById("noPbsPercent");

                          const pbs = filteredPatients.filter(pbs => pbs.baseline === 'Yes').length;
                          const recapture = filteredPatients.filter(pbs => pbs.recapture === 'Yes').length
                          //populate the values
                          apptValue.innerText = filteredPatients.length;
                          baseValue.innerText = pbs;
                          recaptureValue.innerText = recapture;
                          pbsPercent.innerText = Math.round((recapture / pbs) * 100) + "%";
                          noPbs.innerText =  pbs - recapture;
                          noPbsPercent.innerText = Math.round((pbs - recapture) / pbs  * 100) + "%";
                          let tableRow = '';
                          // const currentDate = new Date().toISOString().slice(0, 10);
                          // const currentAppt = data.filter(records => records.date == currentDate);
                          filteredPatients.slice(0, 10).sort((a,b) => a.date > b.date ? -1 : 1).map(patient => {
                              const statusSpan = patient.status === "Pending"
                                  ? `<span class="bg-info-subtle status-span">${patient.status}</span>`
                                  : patient.status === "Appointment kept"
                                      ? `<span class="bg-success-subtle status-span">${patient.status}</span>`
                                      : patient.status === "Missed"
                                          ? `<span class="bg-danger-subtle status-span">${patient.status}</span>`
                                          : null;

                              const base_fp = patient.baseline === "Yes" ? `<i class="fa-solid fa-fingerprint"></i>`
                                  : null;

                              const baseline = data.filter(record => record.baseline === "Yes").length;
                              const recapture = data.filter(record => record.recapture === "Yes").length;
                              const recapture_percent = Math.round((recapture / baseline) * 100);
                              tableRow += `<tr>
                                 <td>
                                  <span class="name-bold">${patient.id}</span>
                                  <span class="inline-block float-end icon fs-4">${base_fp}</span>
                                  <p class="patientName hidden">${patient.name}</p>
                                  <p class="fw-bold">**********</p>
                                 </td>
                                 <td>${titleCase(patient.apptType)} Appointment</td>
                                 <td>${statusSpan}</td>
                                 <td>${patient.date}</td>
                                 <td><a href="/openmrs/coreapps/clinicianfacing/patient.page?patientId=${patient.id}">View details</a></td>
                             </tr>`;
                          });

                          todayAppt.innerHTML = tableRow;
            })



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


