<script>
  let url = "${ ui.actionLink("nmrsappointment", "users", "fetchPatient") }"
</script>
 <div id="newAppt" class="">



     
 </div>
 <main class="mainDiv">
        <div class="patient-result">

            <div class="title">
                <h3>
                    Patient Appointment Module
                </h3>
            </div>
            <div class="appt-header mb-4">
                <div class="filter">
                    <form class="form searchDiv">
                        <div>
                            Start Date: <input type="date" id="startDate" class="form-control">
                        </div>
                        <div>
                            End Date: <input type="date" id="endDate" class="form-control">
                        </div>
                        <div>
                            <a class="btn btn-success btn-search py-2 px-4" onClick="filterPatientAppointment()">
                                Search <i class="fa-solid fa-search"></i>
                            </a>
                        </div>
                    </form>
                </div>
                <div class="btn-Appt">
                    <a  onClick="openAppt()" id="openAppt" class="btn btn-success btn-appt py-2 px-4">
                        Create New Appointment <i class="fa-regular fa-calendar"></i>
                     </a>
                </div>
            </div>


            <div class="landingPage">
                <div>
                    <div class="icon-bg">
                        <i class="fa-solid fa-user-group icon"></i>
                    </div>

                    <h3>Appointments</h3>
                    <h3 class="value" id="apptValue"></h3>
                </div>
                <div>
                    <div class="icon-bg">
                        <i class="fa-solid fa-fingerprint icon"></i>
                    </div>
                    <h3>Recapture / Baseline</h3>
                    <div class="pbs-area">
                        <h3 class="value"><span id="recaptureValue"></span> / <span id="baseValue"></span></h3>
                        <div class="percent bg-success-subtle" id="pbs_percent">
                            <h3>
                                <i class="fa-solid fa-arrow-up"></i>
                                 <span id="pbsPercent"></span>
                            </h3>
                        </div>

                    </div>


                </div>
                <div>
                    <div class="icon-bg">
                        <i class="fa-solid fa-users-viewfinder icon"></i>
                    </div>
                    <h3>Patients without PBS</h3>
                    <div class="pbs-area">
                        <h3 class="value" id="noPbs"></h3>
                        <div class="percent percent-red bg-danger-subtle" id="no_pbs_percent">
                            <h3>
                                <i class="fa-solid fa-arrow-down"></i>
                                <span id="noPbsPercent"></span>
                            </h3>
                       </div>

                    </div>
                </div>
            </div>
            <div class="appt-list">
            <table class="table  table-bordered table-hover">
              <caption class="caption-top captions">
                <span>Recent Appointments </i></span>
                <a href="#" class="float-end">
                    See all Appointments <i class="fa-solid fa-angle-right"></i>
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
    </main>
