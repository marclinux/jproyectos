<template>
  <div class="p-6">
    <h2 class="text-xl font-bold mb-4">Sprints por Proyecto</h2>

    <label for="proyectoSelect" class="block mb-2">Selecciona un proyecto:</label>
    <select v-model="selectedProyectoId" @change="fetchSprints" class="border px-3 py-2 rounded">
      <option disabled value="">-- Selecciona --</option>
      <option v-for="proyecto in proyectos" :key="proyecto.id" :value="proyecto.id">
        {{ proyecto.nombre }}
      </option>
    </select>

    <div v-if="sprint.length" class="mt-6">
      <h3 class="text-lg font-semibold mb-2">Sprints:</h3>
      <ul class="space-y-2">
        <li v-for="sprint in sprint" :key="sprint.id" class="p-4 bg-gray-100 rounded shadow">
          <strong>{{ sprint.nombre }}</strong><br />
          Inicio: {{ sprint.fechaInicio }}<br />
          Fin: {{ sprint.fechaFinal }}
        </li>
      </ul>
    </div>

    <div v-else-if="selectedProyectoId" class="mt-6 text-gray-500">
      No hay sprints para este proyecto.
    </div>
  </div>
</template>