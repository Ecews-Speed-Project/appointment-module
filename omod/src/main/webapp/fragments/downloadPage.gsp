<script>
  let url = "${ ui.actionLink("nmrsappointment", "users", "fetchPatient") }"
</script>
 <div id="newAppt" class="">




 </div>
 <main class="mainDiv">
      <div class="patient-result">

          <div class="title">
              <h3>
                  Download Patient Appointment Line-list
              </h3>
          </div>

          <div class="appt-list">
          <table class="table  table-bordered table-hover">
            <caption class="caption-top captions">
              <span>All Appointments </i></span>
              <a class="btn btn-success btn-search py-2 px-4 float-end" id="downloadButton">
                  Download <i class="fa-solid fa-download"></i>
              </a>
            </caption>

            <thead>
            <tr class="table-head">
              <th class="bg-success-subtle">Name
                  <span id="viewIcon" onClick="toggleNameShow();">
                      <i class="fa-solid fa-eye"></i>
                  </span>
               </th>
              <th class="bg-success-subtle">Appointment Type</th>
              <th class="bg-success-subtle">Status</th>
              <th class="bg-success-subtle">Date</th>
              <th class="bg-success-subtle">Action</th>
            </tr>
            </thead>
            <tbody id="todayAppt" class="appt-landing"></tbody>
          </table>
		</div>
	</div>
 </main>