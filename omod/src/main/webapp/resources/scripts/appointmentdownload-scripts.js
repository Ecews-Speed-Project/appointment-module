document.addEventListener("DOMContentLoaded", () => {
    const getSession = sessionStorage.getItem("isSessionData");
    if (getSession) {
        getAppointmentData()
    } else {
        window.history.back();
    }
});

const formatDate = (dateString) => {
    const date = new Date(dateString);
    date.setUTCHours(date.getUTCHours() + 1);
    const formattedDate = date.toISOString().split('T')[0];

    return formattedDate;
}

function titleCase(str) {
    return str.toLowerCase().split(' ').map(function(word) {
        return (word.charAt(0).toUpperCase() + word.slice(1));
    }).join(' ');
}

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

function getCurrentDateTime() {
  const now = new Date();
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0');
  const day = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  const dateTimeStr = `${year}${month}${day}_${hours}${minutes}${seconds}`;
  return dateTimeStr;
}

function downloadCSV(data, filename) {
    // Get the keys (column names) from the first object
    const keys = Object.keys(data[0]);

    // Prepare the CSV data
    let csvData = keys.join(',') + '\n'; // Add the column names

    // Add the data rows
    data.forEach(row => {
        const values = keys.map(key => row[key] !== undefined ? row[key] : '');
        csvData += values.join(',') + '\n';
    });

    // Create a Blob with the CSV data
    const blob = new Blob([csvData], {
        type: 'text/csv;charset=utf-8;'
    });

    // Create a temporary link element for download
    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    link.setAttribute('download', filename);
    link.style.visibility = 'hidden';
    document.body.appendChild(link);

    // Trigger the download
    link.click();

    // Clean up
    document.body.removeChild(link);
}

function getAppointmentData() {
    const fileName = "NMRS_Appointment_Linelist_" + getCurrentDateTime() + ".csv";
    const downloadButton = document.getElementById("downloadButton")
    const isTodayAppointment = sessionStorage.getItem("todayAppointment");
    const isFilterAppointment = sessionStorage.getItem("filterAppointment");
    if (isTodayAppointment) {
        const todayAppt = document.getElementById("todayAppt");
        let tableRow = '';
        const data = JSON.parse(isTodayAppointment);
        downloadButton.addEventListener('click', () => {
                    downloadCSV(data, fileName);
                });
        data.sort((a, b) => a.nextAppointmentDate > b.nextAppointmentDate ? -1 : 1).map(patient => {
            const statusSpan = patient.status === "Pending" ?
                `<span class="bg-info-subtle status-span">${patient.status}</span>` :
                patient.status === "Appointment kept" ?
                `<span class="bg-success-subtle status-span">${patient.status}</span>` :
                patient.status === "Missed" ?
                `<span class="bg-danger-subtle status-span">${patient.status}</span>` :
                null;

            const base_fp = patient.baseline === "Yes" ? `<i class="fa-solid fa-fingerprint"></i>` :
                null;


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
                            <td><a href="/openmrs/coreapps/clinicianfacing/patient.page?patientId=${patient.lunchView.replace(/_/g, " -")}">View details</a></td>
                        </tr>`;
        });

        todayAppt.innerHTML = tableRow;
    }
    if (isFilterAppointment) {
        let tableRow = '';
        const filteredPatients = JSON.parse(isFilterAppointment);

        downloadButton.addEventListener('click', () => {
            downloadCSV(filteredPatients, fileName);
        });
        filteredPatients.sort((a, b) => a.date > b.date ? -1 : 1).map(patient => {
            const statusSpan = patient.status === "Pending" ?
                `<span class="bg-info-subtle status-span">${patient.status}</span>` :
                patient.status === "Appointment kept" ?
                `<span class="bg-success-subtle status-span">${patient.status}</span>` :
                patient.status === "Missed" ?
                `<span class="bg-danger-subtle status-span">${patient.status}</span>` :
                null;

            const base_fp = patient.baseline === "Yes" ? `<i class="fa-solid fa-fingerprint"></i>` :
                null;


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
    }

}

// Function to download CSV
