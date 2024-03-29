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
           console.log(pbs_percent);
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
               <td><a href="#">View details</a> ${currentAppt.length}</td>
           </tr>`;
       });

        todayAppt.innerHTML = tableRow;
    })
})