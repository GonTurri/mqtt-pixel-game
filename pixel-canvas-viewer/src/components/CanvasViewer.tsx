import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

interface PixelCanvas {
  id: string;
  filas: number;
  columnas: number;
  matriz: string[][];
  target: string[][];
}

interface PixelCanvasCambioDto {
  id: string;
  fila: number;
  columna: number;
  color: string;
}

const CanvasViewer: React.FC = () => {
  const [canvasList, setCanvasList] = useState<PixelCanvas[]>([]);
  const [winners, setWinners] = useState<{ [key: string]: boolean }>({});

  useEffect(() => {
    const alreadySubscribed = new Set<string>();
    const client = new Client({
      brokerURL: "ws://localhost:8080/ws",
      onConnect: () => {
        console.log("✅ Conectado al WebSocket");
  
        client.subscribe("/topic/canvas.todos", (message) => {
          const parsedData: PixelCanvas[] = JSON.parse(message.body);
          setCanvasList(parsedData);
  
          parsedData.forEach((canvas) => {
            if (alreadySubscribed.has(canvas.id)) return;
            alreadySubscribed.add(canvas.id);
  
            client.subscribe(`/topic/canvas.${canvas.id}`, (message) => {
              const cambio: PixelCanvasCambioDto = JSON.parse(message.body);
              setCanvasList((prev) =>
                prev.map((c) => {
                  if (c.id !== cambio.id) return c;
                  const nuevaMatriz = c.matriz.map((row) => [...row]);
                  nuevaMatriz[cambio.fila][cambio.columna] = cambio.color;
                  return { ...c, matriz: nuevaMatriz };
                })
              );
            });
  
            client.subscribe(`/topic/canvas.${canvas.id}.ganador`, () => {
              setWinners((prev) => ({ ...prev, [canvas.id]: true }));
            });
          });
        });
  
        setTimeout(() => {
          client.publish({ destination: "/app/obtenerCanvas" });
        }, 100);
      },
      onStompError: (frame) => {
        console.error("❌ STOMP error:", frame);
      },
    });
  
    client.activate();
  
    return () => {
      client.deactivate();
    };
  }, []);

  return (
    <div className="grid grid-cols-2 gap-8 p-8 min-h-screen">
      {canvasList.length === 0 ? (
        <p className="text-center text-gray-500">No hay canvas disponibles.</p>
      ) : (
        canvasList.map((canvas) => (
          <div
            key={canvas.id}
            className="relative p-4 border rounded-lg shadow-lg bg-white"
          >
            <h2 className="text-lg font-semibold text-center text-gray-700 mb-4">
          Canvas #{canvas.id}
          </h2>
            <div className="flex flex-col gap-4">
              <div>
                <h3 className="text-md font-semibold text-center mb-3">
                  Matriz para colorear
                </h3>
                <div
                  className="grid place-content-center"
                  style={{
                    gridTemplateColumns: `repeat(${canvas.columnas}, 32px)`,
                    gridTemplateRows: `repeat(${canvas.filas}, 32px)`,
                    gap: "2px",
                    display: "grid",
                  }}
                >
                  {canvas.matriz.flat().map((color, index) => (
                    <div
                      key={index}
                      className="w-8 h-8 border border-gray-600"
                      style={{ backgroundColor: color }}
                    ></div>
                  ))}
                </div>
              </div>
              <div>
                <h3 className="text-md text-center font-semibold mt-10 mb-3">
                  Matriz Objetivo
                </h3>
                <div
                  className="grid place-content-center"
                  style={{
                    gridTemplateColumns: `repeat(${canvas.columnas}, 32px)`,
                    gridTemplateRows: `repeat(${canvas.filas}, 32px)`,
                    gap: "2px",
                    display: "grid",
                  }}
                >
                  {canvas.target.flat().map((color, index) => (
                    <div
                      key={index}
                      className="w-8 h-8 border border-gray-600"
                      style={{ backgroundColor: color }}
                    ></div>
                  ))}
                </div>
                {winners[canvas.id] && (
                  <div className="flex text-center bg-green-900 items-center justify-center text-white text-2xl font-bold mt-10">
                    ¡Ganador!
                  </div>
                )}
              </div>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default CanvasViewer;
